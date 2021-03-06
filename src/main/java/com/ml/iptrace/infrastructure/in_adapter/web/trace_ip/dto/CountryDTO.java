package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDTO {
    String name;
    String isoCode;
}
