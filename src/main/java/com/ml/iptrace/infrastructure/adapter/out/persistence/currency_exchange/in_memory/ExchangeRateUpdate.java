package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.in_memory;

import com.ml.iptrace.domain.CurrencyExchangeRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExchangeRateUpdate {
    CurrencyExchangeRate currencyExchangeRate;
    LocalDateTime updated;
}
