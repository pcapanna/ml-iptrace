package com.ml.iptrace.application.in_port.geo_distance.calculate_between_coordinates;

import com.ml.iptrace.domain.DistanceInKilometers;
import io.lettuce.core.GeoCoordinates;

public interface CalculateDistanceBetweenCoordinatesUseCase {

    DistanceInKilometers calculate(GeoCoordinates geoCoordinatesA,
                                GeoCoordinates geoCoordinatesB);

}
