package com.ml.iptrace.application.port.in.country.list_current_times;

import com.ml.iptrace.domain.*;
import io.lettuce.core.GeoCoordinates;
import net.bytebuddy.matcher.CollectionItemMatcher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ListCountryCurrentTimesServiceTest {

    @Autowired
    private ListCountryCurrentTimesService listCountryCurrentTimesService;

    private ZoneId getGermanyZoneId() {
        return ZoneId.of("UTC+01:00");
    }

    private Country getGermany() {
        var germanyCode = CountryCode.of("DE");
        var germanyName = "Germany";
        var EURCurrency = new Currency(CurrencyCode.of("EUR"), "Euro", "€");
        var germanyCurrencies = List.of(EURCurrency);
        var germanyLanguages = List.of(new Language(LanguageCode.of("de"), "Deutsch"));
        var germanyTimeZones = List.of(getGermanyZoneId());
        var germanyGeoCoords = new GeoCoordinates(51, 9);
        Country germany = new Country(
                germanyCode, germanyName, germanyLanguages,
                germanyTimeZones, germanyCurrencies, germanyGeoCoords);
        return germany;
    }

    @Test
    void findGermanyBySomeValidIP() {
        Country germany = getGermany();
        Collection<ZonedDateTime> times = listCountryCurrentTimesService.of(germany);
        ZoneId germanyZoneId = getGermanyZoneId();

        Assert.assertEquals(times.isEmpty(), false);
        Assert.assertEquals(times.size(), 1);
        // Assert that ZoneId is OK and difference between instants is less than 0.1 second
        Assert.assertTrue(times.stream().anyMatch(t -> {
            return t.getZone().equals(germanyZoneId)
                    && t.toInstant().compareTo(Instant.now()) < 100;
        }));

    }

}