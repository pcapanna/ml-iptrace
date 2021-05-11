package com.ml.iptrace.infrastructure.rest_client.country;

import com.ml.iptrace.domain.CountryCode;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryCustomData;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryIsoCodeAndName;
import com.ml.iptrace.infrastructure.rest_client.exception.BadRequestException;

import java.net.InetAddress;

public interface CountryDataRestClient {

    CountryIsoCodeAndName findCountryIsoCodeAndName(InetAddress inetAddress);

    CountryCustomData searchCountryCustomDataByCountryCode(CountryCode code) throws BadRequestException;

}
