package com.ml.iptrace.infrastructure.rest_client.country.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ml.iptrace.domain.Currency;
import com.ml.iptrace.domain.Language;
import com.ml.iptrace.domain.LanguageCode;
import com.ml.iptrace.infrastructure.rest_client.country.data.CountryCustomData;
import io.lettuce.core.GeoCoordinates;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestCountriesResponseDTO {
    String name;
    List<CurrencyDTO> currencies;
    List<String> timezones;
    List<Double> latlng;
    List<LanguagesDTO> languages;

    // TODO: improve this functionality implementing a Mapper class instead
    public CountryCustomData mapToCountryCustomDataResponse() {
        Collection<ZoneId> timezones = this.timezones.stream()
                .map(tz -> ZoneId.of(tz)).collect(Collectors.toList());
        Double latitude = latlng.get(0);
        Double longitude = latlng.get(1);
        GeoCoordinates geoCoordinates = new GeoCoordinates(latitude, longitude);
        Collection<Currency> currencies = this.currencies.stream().map(c -> new Currency(c.code, c.name, c.symbol))
                .collect(Collectors.toList());
        Collection<Language> languages = this.languages.stream().map(langDto ->
                new Language(LanguageCode.of(langDto.getIso639_1()),
                        langDto.getNativeName())).collect(Collectors.toList());
        return new CountryCustomData(name, languages, timezones, currencies, geoCoordinates);
    }

//    private Zon toTimeZone(String timezoneStr) {
//        int offset = 0;
//        if (timezoneStr.contains("+")) {
//            String offsetStr = String.valueOf(timezoneStr.charAt(timezoneStr.indexOf("+") + 1));
//            offset = Integer.parseInt(offsetStr);
//        } else if (timezoneStr.contains("-")) {
//            String offsetStr = String.valueOf(timezoneStr.charAt(timezoneStr.indexOf("-") + 1));
//            offset = -Integer.parseInt(offsetStr);
//        }
//        TimeZone timeZone = TimeZone.getTimeZone("UTC");
//        timeZone.setRawOffset(offset);
//        return timeZone;
//    }

}
