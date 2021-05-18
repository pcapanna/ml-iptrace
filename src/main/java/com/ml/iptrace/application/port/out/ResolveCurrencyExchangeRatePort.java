package com.ml.iptrace.application.port.out;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ResolveCurrencyExchangeRatePort {
    Map<CurrencyCode, Optional<ExchangeRate>> forCurrencies(
            CurrencyCode baseCurrency, Collection<CurrencyCode> currencies);

    Optional<ExchangeRate> forCurrency(CurrencyCode baseCurrency, CurrencyCode currency);
}
