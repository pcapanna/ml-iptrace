package com.ml.iptrace.infrastructure.out_adapter.persistence.country.repository;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;

import java.util.Optional;

public interface CountryRepository  {

    Optional<Country> findByCode(CountryCode code);
    Country save(Country country);
}
