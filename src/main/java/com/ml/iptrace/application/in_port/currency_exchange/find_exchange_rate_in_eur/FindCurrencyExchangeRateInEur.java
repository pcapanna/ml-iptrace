package com.ml.iptrace.application.in_port.currency_exchange.find_exchange_rate_in_eur;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FindCurrencyExchangeRateInEur {

    Optional<ExchangeRate> forCurrency(CurrencyCode currency);
    Map<CurrencyCode, Optional<ExchangeRate>> forCurrencies(List<CurrencyCode> currencies);

}
