package com.ml.iptrace.application.out_port;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;

public interface LoadCountryByCodePort {

    Country loadCountry(CountryCode code);

}
