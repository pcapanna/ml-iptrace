package com.ml.iptrace.application.port.in.country.find_by_code;

import com.ml.iptrace.application.port.out.LoadCountryByCodePort;
import com.ml.iptrace.domain.*;
import io.lettuce.core.GeoCoordinates;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class FindCountryByCodeServiceTest {

    @Autowired
    private FindCountryByCodeService findCountryByCodeService;

    @MockBean
    @Qualifier("countryPersistenceAdapter")
    private LoadCountryByCodePort loadCountryByCodePort;

    private Country getGermany(){
        var germanyCode = CountryCode.of("DE");
        var germanyName = "Germany";
        var EURCurrency = new Currency(CurrencyCode.of("EUR"), "Euro", "€");
        var germanyCurrencies = List.of(EURCurrency);
        var germanyLanguages = List.of(new Language(LanguageCode.of("de"), "Deutsch"));
        var germanyTimeZones = List.of(ZoneId.of("UTC+01:00"));
        var germanyGeoCoords = new GeoCoordinates(51, 9);
        Country germany = new Country(
                germanyCode, germanyName, germanyLanguages,
                germanyTimeZones, germanyCurrencies, germanyGeoCoords);
        return germany;
    }

    @Test
    void findGermanyByCode() {
        var germanyCode = CountryCode.of("DE");
        var germany = getGermany();

        Mockito.when(this.loadCountryByCodePort.loadCountry(germanyCode))
                .thenReturn(germany);
        Country foundCountry = findCountryByCodeService.find(CountryCode.of("DE"));
        Assert.assertEquals(foundCountry, germany);

    }
}