package com.ml.iptrace.infrastructure.out_adapter.persistence.currency_exchange;

import com.ml.iptrace.application.out_port.ResolveCurrencyExchangeRatePort;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.domain.ExchangeRate;
import com.ml.iptrace.infrastructure.PersistenceAdapter;
import com.ml.iptrace.infrastructure.out_adapter.persistence.currency_exchange.repository.CurrencyExchangeRateRepository;
import com.ml.iptrace.infrastructure.out_adapter.persistence.currency_exchange.repository.ExchangeRateUpdate;
import com.ml.iptrace.infrastructure.rest_client.currency_exchange.CurrencyExchangeDataRestClient;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@PersistenceAdapter
@RequiredArgsConstructor
class CurrencyExchangeRatePersistenceAdapter implements ResolveCurrencyExchangeRatePort {

    private final CurrencyExchangeRateRepository currencyExchangeRateRepository;
    // Since current Qualifier limits request to 1000 a month
    // It's decided that we´ll only fetch 1 rate currency pair per '1 Day unit of time'.
    private final CurrencyExchangeDataRestClient currencyExchangeRestClient;

    @Override
    public Map<CurrencyCode, Optional<ExchangeRate>>
    forCurrencies(CurrencyCode baseCurrency, Collection<CurrencyCode> currencies) {
        Collection<CurrencyCode> currenciesToFetch = new LinkedList<>();
        Map<CurrencyCode, Optional<ExchangeRate>> result = new HashMap<>();
        currencies.forEach(currency -> {
            Optional<ExchangeRateUpdate> exchangeRateUpdate =
                    currencyExchangeRateRepository.findForCurrency(baseCurrency, currency);
            if (exchangeRateUpdate.isPresent()) {
                LocalDateTime timeUpdated = exchangeRateUpdate.get().getUpdated();
                if (timeUpdated.isBefore(LocalDateTime.now().minus(1, ChronoUnit.DAYS))) {
                    currenciesToFetch.add(currency);
                } else {
                    result.put(currency, exchangeRateUpdate.get().getCurrencyExchangeRate().getExchangeRate());
                }
            } else {
                currenciesToFetch.add(currency);
            }
        });
        if (!currenciesToFetch.isEmpty()) {
            Collection<CurrencyExchangeRate> fetchedRates =
                    currencyExchangeRestClient.findExchangeRates(baseCurrency, currenciesToFetch);
            fetchedRates.forEach(updatedRate -> {
                currencyExchangeRateRepository.save(baseCurrency, updatedRate);
                result.put(updatedRate.getCurrencyCode(), updatedRate.getExchangeRate());
            });
        }
        return result;
    }

    @Override
    public Optional<ExchangeRate> forCurrency(CurrencyCode baseCurrency, CurrencyCode currency) {
        return this.forCurrencies(baseCurrency, List.of(currency)).get(currency);
    }
}
