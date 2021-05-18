package com.ml.iptrace.application.port.in.currency_exchange.find_exchange_rate;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.port.out.ResolveCurrencyExchangeRatePort;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FindCurrencyExchangeRateService implements FindCurrencyExchangeRateUseCase {

    @Autowired
    @Qualifier("redisExchangeRatePersistenceAdapter")
    private ResolveCurrencyExchangeRatePort resolveCurrencyExchangeRatePort;

    @Override
    public Optional<ExchangeRate>
    fromBaseCurrencyToCurrency(CurrencyCode baseCurrency, CurrencyCode currency) {
        return resolveCurrencyExchangeRatePort.forCurrency(baseCurrency, currency);
    }

    @Override
    public Map<CurrencyCode, Optional<ExchangeRate>>
    fromBaseCurrencyToCurrencies(CurrencyCode baseCurrency, Collection<CurrencyCode> currencies) {
        return resolveCurrencyExchangeRatePort.forCurrencies(baseCurrency, currencies);
    }
}
