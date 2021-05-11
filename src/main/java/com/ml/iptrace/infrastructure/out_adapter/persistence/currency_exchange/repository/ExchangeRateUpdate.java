package com.ml.iptrace.infrastructure.out_adapter.persistence.currency_exchange.repository;

import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExchangeRateUpdate {
    CurrencyExchangeRate currencyExchangeRate;
    LocalDateTime updated;
}
