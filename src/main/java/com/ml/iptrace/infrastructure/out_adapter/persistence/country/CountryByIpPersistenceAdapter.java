package com.ml.iptrace.infrastructure.out_adapter.persistence.country;

import com.ml.iptrace.application.out_port.ResolveCountryCodeByIpPort;
import com.ml.iptrace.domain.CountryCode;
import com.ml.iptrace.infrastructure.PersistenceAdapter;
import com.ml.iptrace.infrastructure.out_adapter.persistence.country.repository.CountryCodeByIpRepository;
import com.ml.iptrace.infrastructure.rest_client.country.CountryDataRestClient;
import lombok.RequiredArgsConstructor;

import java.net.InetAddress;


@PersistenceAdapter
@RequiredArgsConstructor
class CountryByIpPersistenceAdapter implements ResolveCountryCodeByIpPort {

    private final CountryCodeByIpRepository countryCodeByIpRepository;
    private final CountryDataRestClient countryDataRestClient;

    @Override
    public CountryCode resolveCountryCodeByIp(InetAddress inetAddress) {
        return countryCodeByIpRepository.findCountryCodeByIp(inetAddress).orElseGet(() -> {
            CountryCode countryCode = CountryCode.of(countryDataRestClient
                    .findCountryIsoCodeAndName(inetAddress).getIsoCode());
            countryCodeByIpRepository.save(inetAddress, countryCode);
            return countryCode;
        });
    }

}
