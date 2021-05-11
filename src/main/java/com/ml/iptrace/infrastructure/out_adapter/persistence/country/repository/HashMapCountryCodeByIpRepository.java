package com.ml.iptrace.infrastructure.out_adapter.persistence.country.repository;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
class HashMapCountryCodeByIpRepository implements CountryCodeByIpRepository {

    private final ConcurrentHashMap<String, CountryCode> memoryMap = new ConcurrentHashMap<>();

    @Override
    public Optional<CountryCode> findCountryCodeByIp(InetAddress inetAddress) {
        return Optional.ofNullable(memoryMap.get(inetAddress.getHostAddress()));
    }

    @Override
    public void save(InetAddress inetAddress, CountryCode countryCode) {
        memoryMap.put(inetAddress.getHostAddress(), countryCode);
    }
}
