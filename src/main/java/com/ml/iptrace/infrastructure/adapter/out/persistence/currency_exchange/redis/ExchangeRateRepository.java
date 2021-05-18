package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.domain.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRateForCurrency> findForCurrency(CurrencyCode atCurrency, CurrencyCode forCurrency);

    void save(CurrencyCode atCurrency, CurrencyCode forCurrency, Optional<ExchangeRate> exchangeRate);

    void delete(CurrencyCode atCurrency, CurrencyCode forCurrency);

    void deleteAll();
}
