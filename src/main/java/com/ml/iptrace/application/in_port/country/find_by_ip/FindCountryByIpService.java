package com.ml.iptrace.application.in_port.country.find_by_ip;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.out_port.LoadCountryByCodePort;
import com.ml.iptrace.application.out_port.ResolveCountryCodeByIpPort;
import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.net.InetAddress;

@UseCase
@RequiredArgsConstructor
public class FindCountryByIpService implements FindCountryByIpUseCase {

    @Autowired
    @Qualifier("countryByIpPersistenceAdapter")
    private ResolveCountryCodeByIpPort resolveCountryCodeByIpPort;
    @Autowired
    @Qualifier("countryPersistenceAdapter")
    private LoadCountryByCodePort loadCountryByCodePort;

    @Override
    public Country find(InetAddress inetAddress) {
        CountryCode countryCode = resolveCountryCodeByIpPort.resolveCountryCodeByIp(inetAddress);
        Country country = loadCountryByCodePort.loadCountry(countryCode);
        return country;
    }

}
