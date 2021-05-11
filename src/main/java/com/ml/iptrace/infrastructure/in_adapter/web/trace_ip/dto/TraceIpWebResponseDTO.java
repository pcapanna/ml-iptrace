package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ml.iptrace.application.in_port.ip.trace.TraceIpResponse;
import com.ml.iptrace.application.in_port.trace_stats.get_distance_stats.TraceDistanceStatsResponse;
import com.ml.iptrace.common.JsonZonedTimeSerializer;
import com.ml.iptrace.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraceIpWebResponseDTO {
    String ip;
    String countryName;
    String countryCode;
    Collection<Language> countryLanguages;
    Collection<String> countryCurrencies;
    @JsonSerialize(contentUsing = JsonZonedTimeSerializer.class)
    Collection<ZonedDateTime> countryCurrentTimes;
    String estimatedDistanceBetweenCountry;

    // Could be implemented just the same with a MapperClass
    public TraceIpWebResponseDTO(String ip, TraceIpResponse traceIpResponse) {
        Country country = traceIpResponse.getCountry();
        Collection<ZonedDateTime> currentCountryTimes = traceIpResponse.getCurrentCountryTimes();
        DistanceInKilometers distanceInKilometers = traceIpResponse.getDistanceInKilometers();
        Map<CurrencyCode, Optional<ExchangeRate>> exchangeRates
                = traceIpResponse.getCountryCurrenciesExchangeRates();

        this.ip = ip;
        this.countryName = country.getName();
        this.countryCode = country.getCode().toString();
        this.countryLanguages = country.getLanguages();
        this.countryCurrencies = country.getCurrencies().stream().map(
                curr -> {
                    Optional<ExchangeRate> exchangeRate = exchangeRates.get(curr.getCode());
                    String exchangeStr = exchangeRate.map(rate ->
                            " (1 " + curr.getCode() + " = " + rate.getValue() + " EUR)").orElse("");
                    return curr.getCode().toString().concat(exchangeStr);
                }

        ).collect(Collectors.toList());
        this.countryCurrentTimes = currentCountryTimes;
        this.estimatedDistanceBetweenCountry = distanceInKilometers.toString();
    }

}
