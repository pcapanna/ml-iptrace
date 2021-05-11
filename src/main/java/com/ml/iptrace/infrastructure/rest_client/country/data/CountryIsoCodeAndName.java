package com.ml.iptrace.infrastructure.rest_client.country.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class CountryIsoCodeAndName {
    String isoCode;
    String name;
}
