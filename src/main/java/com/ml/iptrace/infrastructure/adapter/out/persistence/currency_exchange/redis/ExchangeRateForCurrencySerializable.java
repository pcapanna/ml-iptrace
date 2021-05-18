package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.DistanceInKilometers;
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
public class ExchangeRateForCurrencySerializable implements Serializable {
    CurrencyCode forCurrency;
    CurrencyCode atCurrency;
    //nullable
    ExchangeRate exchangeRate;
    LocalDateTime updated;

    public ExchangeRateForCurrency toEntity(){
        return new ExchangeRateForCurrency(forCurrency, atCurrency, Optional.ofNullable(exchangeRate), updated);
    }

    public static ExchangeRateForCurrencySerializable of(ExchangeRateForCurrency entity) {
        return new ExchangeRateForCurrencySerializable(
                entity.forCurrency,
                entity.atCurrency,
                entity.exchangeRate.orElse(null),
                entity.updated);
    }
}
