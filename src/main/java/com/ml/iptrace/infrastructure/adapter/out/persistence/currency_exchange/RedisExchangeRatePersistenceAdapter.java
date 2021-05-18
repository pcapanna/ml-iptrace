package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange;

import com.ml.iptrace.application.port.out.ResolveCurrencyExchangeRatePort;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.domain.ExchangeRate;
import com.ml.iptrace.infrastructure.PersistenceAdapter;
import com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.in_memory.CurrencyExchangeRateRepository;
import com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.in_memory.ExchangeRateUpdate;
import com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis.ExchangeRateForCurrency;
import com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis.ExchangeRateRepository;
import com.ml.iptrace.infrastructure.service.rest_client.currency_exchange.CurrencyExchangeDataRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@PersistenceAdapter
@RequiredArgsConstructor
class RedisExchangeRatePersistenceAdapter implements ResolveCurrencyExchangeRatePort {

    @Qualifier("exchangeRateForCurrencyRepository")
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyExchangeDataRestClient currencyExchangeRestClient;

    @Override
    public Map<CurrencyCode, Optional<ExchangeRate>>
    forCurrencies(CurrencyCode baseCurrency, Collection<CurrencyCode> currencies) {
        Collection<CurrencyCode> currenciesToFetch = new LinkedList<>();
        Map<CurrencyCode, Optional<ExchangeRate>> result = new HashMap<>();
        currencies.forEach(forCurrency -> {
            Optional<ExchangeRateForCurrency> exchangeRateForCurrency =
                    exchangeRateRepository.findForCurrency(baseCurrency, forCurrency);
            if (exchangeRateForCurrency.isPresent()) {
                result.put(forCurrency, exchangeRateForCurrency.get().getExchangeRate());
            } else {
                currenciesToFetch.add(forCurrency);
            }
        });
        if (!currenciesToFetch.isEmpty()) {
            Collection<CurrencyExchangeRate> fetchedRates =
                    currencyExchangeRestClient.findExchangeRates(baseCurrency, currenciesToFetch);
            fetchedRates.forEach(updatedRate -> {
                exchangeRateRepository.save(
                        baseCurrency,
                        updatedRate.getCurrencyCode(),
                        updatedRate.getExchangeRate());
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
