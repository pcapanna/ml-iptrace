package com.ml.iptrace.application.port.in.geo_distance.calculate_between_country_to_bsas;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.DistanceInKilometers;

public interface CalculateDistanceBetweenCountryToBsAsUseCase {

    DistanceInKilometers from(Country country);

}