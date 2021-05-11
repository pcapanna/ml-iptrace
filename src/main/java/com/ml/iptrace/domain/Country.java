package com.ml.iptrace.domain;

import io.lettuce.core.GeoCoordinates;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.ZoneId;
import java.util.Collection;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Country {
    @Id
    @NonNull CountryCode code;
    @NonNull String name;
    @NonNull Collection<Language> languages;
    @NonNull Collection<ZoneId> timeZones;
    @NonNull Collection<Currency> currencies;
    @NonNull GeoCoordinates geoCoordinates;
}
