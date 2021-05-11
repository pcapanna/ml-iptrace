package com.ml.iptrace.infrastructure.out_adapter.persistence.country;

import com.ml.iptrace.application.out_port.LoadCountryByCodePort;
import com.ml.iptrace.application.out_port.ResolveCountryCodeByIpPort;
import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import com.ml.iptrace.infrastructure.PersistenceAdapter;
import com.ml.iptrace.infrastructure.rest_client.country.CountryDataRestClient;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryCustomData;
import com.ml.iptrace.infrastructure.out_adapter.persistence.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.net.InetAddress;


@PersistenceAdapter
@RequiredArgsConstructor
class CountryPersistenceAdapter implements LoadCountryByCodePort {

    private final CountryRepository countryRepository;
    private final CountryDataRestClient countryDataRestClient;

    @Override
    public Country loadCountry(CountryCode code) {
        return countryRepository.findByCode(code).orElseGet(() -> {
            CountryCustomData customData = countryDataRestClient
                    .searchCountryCustomDataByCountryCode(code);
            Country country = new Country(
                    code,
                    customData.getName(),
                    customData.getLanguages(),
                    customData.getTimezones(),
                    customData.getCurrencies(),
                    customData.getGeoCoordinates());
            countryRepository.save(country);
            return country;
        });
    }

}
