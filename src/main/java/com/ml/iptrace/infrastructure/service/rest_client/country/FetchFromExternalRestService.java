package com.ml.iptrace.infrastructure.service.rest_client.country;

import com.ml.iptrace.domain.CountryCode;
import com.ml.iptrace.infrastructure.service.rest_client.country.data.CountryCustomData;
import com.ml.iptrace.infrastructure.service.rest_client.country.data.CountryIsoCodeAndName;
import com.ml.iptrace.infrastructure.service.rest_client.country.dto.Ip2CountryResponseDTO;
import com.ml.iptrace.infrastructure.service.rest_client.country.dto.RestCountriesResponseDTO;
import com.ml.iptrace.infrastructure.service.rest_client.currency_exchange.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FetchFromExternalRestService implements CountryDataRestClient {

    private final static String IP_TO_COUNTRY_URL = "https://api.ip2country.info/ip";
    private final static String REST_COUNTRIES_URL = "https://restcountries.eu/rest/v2/alpha";

    @Override
    public CountryIsoCodeAndName findCountryIsoCodeAndName(InetAddress inetAddress) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Ip2CountryResponseDTO> response = restTemplate.getForEntity(
                    IP_TO_COUNTRY_URL.concat("?{ip}"),
                    Ip2CountryResponseDTO.class,
                    Map.of("ip", inetAddress.getHostAddress()));
            return response.getBody().mapToCountryIsoCodeAndNameResponse();
            // TODO: Check the isoCode to check if it´s a country or continent. throw Exception if it´s Continent
            // After testing this endpoint I noticed that some IPs gives me Continents ISO CODE instead of Country
        } catch (RestClientException exception) {
            // Let´s blame that IP
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public CountryCustomData searchCountryCustomDataByCountryCode(CountryCode code) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<RestCountriesResponseDTO[]> response = restTemplate.getForEntity(
                    REST_COUNTRIES_URL.concat("?fields=name;timezones;currencies;latlng;languages&codes=" + code.toString()),
                    RestCountriesResponseDTO[].class,
                    Map.of("code", code.toString()));
            return List.of(response.getBody()).stream().findAny()
                    .map(RestCountriesResponseDTO::mapToCountryCustomDataResponse)
                    .orElseThrow();
        } catch (HttpServerErrorException exception) {
            // For some reason it the endpoint gives me 500 internal error when code is invalid.
            // I will assume that it´s the only reason for this kind of error
            // until I find some other endpoint to replace this faulty one.
            if (exception.getRawStatusCode() == 500) {
                throw new RuntimeException("Cannot resolve Country Information");
            } else {
                throw exception;
            }
        } catch (HttpClientErrorException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }
}
