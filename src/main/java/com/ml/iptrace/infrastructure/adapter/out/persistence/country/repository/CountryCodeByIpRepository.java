package com.ml.iptrace.infrastructure.adapter.out.persistence.country.repository;

import com.ml.iptrace.domain.CountryCode;

import java.net.InetAddress;
import java.util.Optional;

public interface CountryCodeByIpRepository {

    Optional<CountryCode> findCountryCodeByIp(InetAddress inetAddress);
    void save(InetAddress inetAddress, CountryCode countryCode);
}
