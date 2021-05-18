package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.in_memory;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;

import java.util.Optional;

public interface CurrencyExchangeRateRepository {
    Optional<ExchangeRateUpdate> findForCurrency(CurrencyCode baseCurrency, CurrencyCode currencyCode);

    void save(CurrencyCode baseCurrency, CurrencyExchangeRate currencyExchangeRate);
}
