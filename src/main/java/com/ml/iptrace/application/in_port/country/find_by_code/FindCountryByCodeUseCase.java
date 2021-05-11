package com.ml.iptrace.application.in_port.country.find_by_code;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;


public interface FindCountryByCodeUseCase {

    Country find(CountryCode code);

}
