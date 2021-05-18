package com.ml.iptrace.infrastructure.adapter.out.rest_client.currency_exchange;

import com.ml.iptrace.application.port.out.ResolveCurrencyExchangeRatePort;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.domain.ExchangeRate;
import com.ml.iptrace.infrastructure.RestClientAdapter;
import com.ml.iptrace.infrastructure.service.rest_client.currency_exchange.CurrencyExchangeDataRestClient;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestClientAdapter
@RequiredArgsConstructor
public class CurrencyExchangeRateRestClientAdapter implements ResolveCurrencyExchangeRatePort {

    private final CurrencyExchangeDataRestClient currencyExchangeDataRestClient;

    @Override
    public Map<CurrencyCode, Optional<ExchangeRate>> forCurrencies(
            CurrencyCode baseCurrency, Collection<CurrencyCode> currencies) {
        return currencyExchangeDataRestClient.findExchangeRates(baseCurrency, currencies).stream().collect(Collectors
                .toMap(CurrencyExchangeRate::getCurrencyCode, CurrencyExchangeRate::getExchangeRate
        ));
    }

    @Override
    public Optional<ExchangeRate> forCurrency(CurrencyCode baseCurrency, CurrencyCode currency) {
        // Function called should always return value.
        // If OUT OF BOUNDS EXCEPTION, something is seriously wrong
        return forCurrencies(baseCurrency, List.of(currency)).get(0);
    }
}
