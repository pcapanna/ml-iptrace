package com.ml.iptrace.application.in_port.geo_distance.calculate_between_country_to_bsas;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.in_port.geo_distance.calculate_between_coordinates.CalculateDistanceBetweenCoordinatesUseCase;
import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.DistanceInKilometers;
import io.lettuce.core.GeoCoordinates;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CalculateDistanceBetweenCountryToBsAsService implements CalculateDistanceBetweenCountryToBsAsUseCase {

    private final CalculateDistanceBetweenCoordinatesUseCase calculateDistanceBetweenCoordinatesUseCase;
    private final GeoCoordinates BS_AS_GEO_COORDINATES = new GeoCoordinates(
            -34.6037232,
            -58.3815931
    );

    @Override
    public DistanceInKilometers from(Country country) {
        return calculateDistanceBetweenCoordinatesUseCase
                .calculate(country.getGeoCoordinates(),BS_AS_GEO_COORDINATES);
    }
}
