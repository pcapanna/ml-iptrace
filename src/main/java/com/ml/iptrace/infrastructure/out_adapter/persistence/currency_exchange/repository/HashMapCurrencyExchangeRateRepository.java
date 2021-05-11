package com.ml.iptrace.infrastructure.out_adapter.persistence.currency_exchange.repository;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
class HashMapCurrencyExchangeRateRepository implements CurrencyExchangeRateRepository {

    private final static Integer THREEHOLD = 10000;
    private final ConcurrentMap<ExchangeRateKey, ExchangeRateUpdate> memoryMap =
            new ConcurrentHashMap<>();

    @Override
    public Optional<ExchangeRateUpdate> findForCurrency(CurrencyCode baseCurrency, CurrencyCode currency) {
        return Optional.ofNullable(memoryMap.get(new ExchangeRateKey(baseCurrency, currency)));
    }

    @Override
    public void save(CurrencyCode baseCurrency, CurrencyExchangeRate currencyExchangeRate) {
        ExchangeRateUpdate newestRate =
                new ExchangeRateUpdate(currencyExchangeRate, LocalDateTime.now());
        newestRate.setUpdated(LocalDateTime.now());
        memoryMap.put(new ExchangeRateKey(baseCurrency, newestRate.getCurrencyExchangeRate().getCurrencyCode()), newestRate);
    }

    @AllArgsConstructor
    @Value
    private class ExchangeRateKey {
        private final CurrencyCode baseCurrency;
        private final CurrencyCode forCurrency;
    }

}

