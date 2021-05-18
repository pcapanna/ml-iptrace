package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash("ExchangeRateForCurrency")
public class ExchangeRateForCurrency implements Serializable {
    CurrencyCode forCurrency;
    CurrencyCode atCurrency;
    Optional<ExchangeRate> exchangeRate;
    LocalDateTime updated;
}
