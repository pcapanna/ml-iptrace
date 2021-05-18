package com.ml.iptrace.application.port.in.country.find_by_code;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CountryCode;


public interface FindCountryByCodeUseCase {

    Country find(CountryCode code);

}
