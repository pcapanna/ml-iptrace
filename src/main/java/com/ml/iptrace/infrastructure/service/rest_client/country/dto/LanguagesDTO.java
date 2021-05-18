package com.ml.iptrace.infrastructure.service.rest_client.country.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguagesDTO {
    String iso639_1;
    String iso639_2;
    String name;
    String nativeName;
}
