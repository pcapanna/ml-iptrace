package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Repository
public class ExchangeRateForCurrencyRepository implements ExchangeRateRepository {

    public static final String HASH_KEY = "ExchangeRateForCurrency";

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    // Remove exchange rate if saved for more than 1 day
    @Override
    public Optional<ExchangeRateForCurrency> findForCurrency(CurrencyCode atCurrency, CurrencyCode forCurrency) {
        ExchangeRateForCurrencySerializable exchangeRateForCurrencySerializable =
                (ExchangeRateForCurrencySerializable) template.opsForHash().get(HASH_KEY, getHashId(atCurrency, forCurrency));
        if (exchangeRateForCurrencySerializable != null
                && exchangeRateForCurrencySerializable.updated.isBefore(LocalDateTime.now().minus(1, ChronoUnit.DAYS))) {
            delete(atCurrency, forCurrency);
            return Optional.empty();
        } else if (exchangeRateForCurrencySerializable == null) {
            return Optional.empty();
        }else{
            return Optional.of(exchangeRateForCurrencySerializable.toEntity());
        }
    }

    @Override
    public void save(CurrencyCode atCurrency, CurrencyCode forCurrency, Optional<ExchangeRate> exchangeRate) {
        var exchangeRateForCurrency =
                new ExchangeRateForCurrency(atCurrency, forCurrency, exchangeRate, LocalDateTime.now());
        template.opsForHash().put(HASH_KEY,
                getHashId(atCurrency, forCurrency),
                ExchangeRateForCurrencySerializable.of(exchangeRateForCurrency));

    }

    @Override
    public void delete(CurrencyCode atCurrency, CurrencyCode forCurrency) {
        template.opsForHash().delete(HASH_KEY, getHashId(atCurrency, forCurrency));
    }

    @Override
    public void deleteAll() {
        template.opsForHash().delete(HASH_KEY);

    }

    private String getHashId(CurrencyCode atCurrency, CurrencyCode forCurrency) {
        return atCurrency.toString() + "_" + forCurrency.toString();
    }
}
