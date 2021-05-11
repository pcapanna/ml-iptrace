package com.ml.iptrace.infrastructure.rest_client.country.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ml.iptrace.domain.CurrencyCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {
    CurrencyCode code;
    String name;
    String symbol;
}
