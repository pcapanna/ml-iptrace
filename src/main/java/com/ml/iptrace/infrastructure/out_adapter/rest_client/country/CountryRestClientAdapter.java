package com.ml.iptrace.infrastructure.out_adapter.rest_client.country;

import com.ml.iptrace.application.out_port.LoadCountryByCodePort;
import com.ml.iptrace.application.out_port.ResolveCountryCodeByIpPort;
import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import com.ml.iptrace.infrastructure.RestClientAdapter;
import com.ml.iptrace.infrastructure.rest_client.country.CountryDataRestClient;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryCustomData;
import lombok.RequiredArgsConstructor;

import java.net.InetAddress;


@RestClientAdapter
@RequiredArgsConstructor
class CountryRestClientAdapter implements ResolveCountryCodeByIpPort, LoadCountryByCodePort {

    private final CountryDataRestClient countryDataRestClient;

    @Override
    public Country loadCountry(CountryCode code) {
        CountryCustomData customData = countryDataRestClient
                .searchCountryCustomDataByCountryCode(code);
        Country country = new Country(
                code,
                customData.getName(),
                customData.getLanguages(),
                customData.getTimezones(),
                customData.getCurrencies(),
                customData.getGeoCoordinates());
        return country;
    }

    @Override
    public CountryCode resolveCountryCodeByIp(InetAddress inetAddress) {
        return CountryCode.of(countryDataRestClient
                .findCountryIsoCodeAndName(inetAddress).getIsoCode());
    }

}
