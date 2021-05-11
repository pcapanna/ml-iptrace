package com.ml.iptrace.application.in_port.country.find_by_code;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.out_port.LoadCountryByCodePort;
import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UseCase
@RequiredArgsConstructor
public class FindCountryByCodeService implements FindCountryByCodeUseCase {

    @Autowired
    @Qualifier("countryPersistenceAdapter")
    private LoadCountryByCodePort loadCountryByCodePort;

    @Override
    public Country find(CountryCode code) {
        return loadCountryByCodePort.loadCountry(code);
    }
}
