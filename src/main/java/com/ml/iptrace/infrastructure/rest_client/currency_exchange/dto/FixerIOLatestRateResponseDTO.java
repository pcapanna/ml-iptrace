package com.ml.iptrace.infrastructure.rest_client.currency_exchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.ExchangeRate;
import com.ml.iptrace.domain.CurrencyExchangeRate;
import lombok.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerIOLatestRateResponseDTO {
    boolean success;
    Map<String, Double> rates;

    // TODO: improve this functionality implementing a Mapper class instead
    public Collection<CurrencyExchangeRate> mapToCurrencyExchangeRates() {
        Collection<CurrencyExchangeRate> exchangeRates =
                this.rates.entrySet().stream().map(rateEntry ->
                        new CurrencyExchangeRate(
                                CurrencyCode.of(rateEntry.getKey()),
                                Optional.ofNullable(ExchangeRate.of(rateEntry.getValue()))
                        ))
                .collect(Collectors.toList());
        return exchangeRates;
    }

}
