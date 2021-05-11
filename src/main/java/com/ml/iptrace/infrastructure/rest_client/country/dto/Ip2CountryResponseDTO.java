package com.ml.iptrace.infrastructure.rest_client.country.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryIsoCodeAndName;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ip2CountryResponseDTO {
    String countryCode;
    String countryCode3;
    String countryName;

//    // TODO: improve this functionality implementing a Mapper class instead
    public CountryIsoCodeAndName mapToCountryIsoCodeAndNameResponse(){
        return new CountryIsoCodeAndName(this.getCountryCode(), this.getCountryName());
    }
}
