package com.ml.iptrace.application.port.in.country.find_by_ip;

import com.ml.iptrace.application.port.in.country.find_by_code.FindCountryByCodeService;
import com.ml.iptrace.application.port.out.LoadCountryByCodePort;
import com.ml.iptrace.application.port.out.ResolveCountryCodeByIpPort;
import com.ml.iptrace.domain.*;
import io.lettuce.core.GeoCoordinates;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.time.ZoneId;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class FindCountryByIpServiceTest {

    @Autowired
    private FindCountryByIpService findCountryByIpService;

    @MockBean
    @Qualifier("countryByIpPersistenceAdapter")
    private ResolveCountryCodeByIpPort resolveCountryCodeByIpPort;
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

    @SneakyThrows
    @Test
    void findGermanyBySomeValidIP() {
        InetAddress someGermanyValidIP = InetAddress.getByName("5.6.7.8");
        var germanyCode = CountryCode.of("DE");
        var germany = getGermany();

        Mockito.when(this.resolveCountryCodeByIpPort.resolveCountryCodeByIp(someGermanyValidIP))
                .thenReturn(germanyCode);
        Mockito.when(this.loadCountryByCodePort.loadCountry(germanyCode))
                .thenReturn(germany);
        Country foundCountry = findCountryByIpService.find(someGermanyValidIP);
        Assert.assertEquals(foundCountry, germany);
    }
}