package com.ml.iptrace.infrastructure.service.rest_client.country.data;

import com.ml.iptrace.domain.Currency;
import com.ml.iptrace.domain.Language;
import io.lettuce.core.GeoCoordinates;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.util.Collection;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class CountryCustomData {
    String name;
    Collection<Language> languages;
    Collection<ZoneId> timezones;
    Collection<Currency> currencies;
    GeoCoordinates geoCoordinates;
}
