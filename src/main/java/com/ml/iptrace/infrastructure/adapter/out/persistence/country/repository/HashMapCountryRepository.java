package com.ml.iptrace.infrastructure.adapter.out.persistence.country.repository;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
class HashMapCountryRepository implements CountryRepository {

    private final ConcurrentHashMap<CountryCode, Country> memoryMap = new ConcurrentHashMap<>();

    @Override
    public Optional<Country> findByCode(CountryCode code) {
        return Optional.ofNullable(memoryMap.get(code));
    }

    @Override
    public Country save(Country country) {
        return memoryMap.put(country.getCode(), country);
    }
}
