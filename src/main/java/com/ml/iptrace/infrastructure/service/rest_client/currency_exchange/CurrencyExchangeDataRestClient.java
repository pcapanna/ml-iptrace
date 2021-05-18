package com.ml.iptrace.infrastructure.service.rest_client.currency_exchange;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;

import java.util.Collection;

public interface CurrencyExchangeDataRestClient {

    Collection<CurrencyExchangeRate> findExchangeRates(
            CurrencyCode baseCurrency,
            Collection<CurrencyCode> currencies);

}
