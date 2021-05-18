package com.ml.iptrace.infrastructure.service.rest_client.currency_exchange;

import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import com.ml.iptrace.infrastructure.service.rest_client.currency_exchange.dto.FixerIOLatestRateResponseDTO;
import com.ml.iptrace.infrastructure.service.rest_client.currency_exchange.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Current implementation limits number of request to 1000 a Month.
 * .. Truly bad, use it with consideration.
 */
@Component
@RequiredArgsConstructor
public class CurrencyExchangeDataRestClientImpl implements CurrencyExchangeDataRestClient {

    private final static String FIXER_IO_LATEST_RATE_URL = "http://data.fixer.io/api/latest";
    private final static String FIXER_IO_API_KEY = "c4deaf3d74612245a281135c8f383415";

    @Override
    public Collection<CurrencyExchangeRate> findExchangeRates(
            CurrencyCode baseCurrency, Collection<CurrencyCode> currencies) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<FixerIOLatestRateResponseDTO> response = restTemplate.getForEntity(
                    FIXER_IO_LATEST_RATE_URL.concat("?base={baseCurrency}&symbols={currencies}&access_key={apiKey}"),
                    FixerIOLatestRateResponseDTO.class,
                    Map.of(
                            "baseCurrency", baseCurrency.toString(),
                            "currencies", currencies.stream()
                                    .map(CurrencyCode::toString)
                                    .collect(Collectors.joining(";")),
                            "apiKey", FIXER_IO_API_KEY));
            if (!response.getBody().isSuccess()) {
                return currencies.stream()
                        .map(currency -> new CurrencyExchangeRate(currency, Optional.empty()))
                        .collect(Collectors.toList());
            }
            return response.getBody().mapToCurrencyExchangeRates();
        } catch (RestClientException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }
}
