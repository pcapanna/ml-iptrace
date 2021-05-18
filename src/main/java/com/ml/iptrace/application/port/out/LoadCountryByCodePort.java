package com.ml.iptrace.application.port.out;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;

public interface LoadCountryByCodePort {

    Country loadCountry(CountryCode code);

}
