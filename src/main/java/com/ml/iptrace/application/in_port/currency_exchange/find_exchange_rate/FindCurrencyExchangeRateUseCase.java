package com.ml.iptrace.application.in_port.currency_exchange.find_exchange_rate;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface FindCurrencyExchangeRateUseCase {

    Optional<ExchangeRate> fromBaseCurrencyToCurrency(
            CurrencyCode baseCurrency, CurrencyCode forCurrencies);

    Map<CurrencyCode, Optional<ExchangeRate>> fromBaseCurrencyToCurrencies(
            CurrencyCode baseCurrency, Collection<CurrencyCode> forCurrencies);

}
