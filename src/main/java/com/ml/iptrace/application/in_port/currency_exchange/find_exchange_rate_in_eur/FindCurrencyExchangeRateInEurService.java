package com.ml.iptrace.application.in_port.currency_exchange.find_exchange_rate_in_eur;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.in_port.currency_exchange.find_exchange_rate.FindCurrencyExchangeRateUseCase;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FindCurrencyExchangeRateInEurService implements FindCurrencyExchangeRateInEur {

    private final FindCurrencyExchangeRateUseCase findCurrencyExchangeRateUseCase;
    private final static CurrencyCode EUR = CurrencyCode.of("EUR");

    public Optional<ExchangeRate> forCurrency(CurrencyCode currency) {
        return findCurrencyExchangeRateUseCase.fromBaseCurrencyToCurrency(currency, EUR);
    }

    @Override
    public Map<CurrencyCode, Optional<ExchangeRate>> forCurrencies(List<CurrencyCode> currencies) {
        return findCurrencyExchangeRateUseCase.fromBaseCurrencyToCurrencies(EUR, currencies);
    }


}
