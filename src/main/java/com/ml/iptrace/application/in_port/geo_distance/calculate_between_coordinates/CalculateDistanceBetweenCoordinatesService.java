package com.ml.iptrace.application.in_port.geo_distance.calculate_between_coordinates;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.domain.DistanceInKilometers;
import io.lettuce.core.GeoCoordinates;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CalculateDistanceBetweenCoordinatesService implements CalculateDistanceBetweenCoordinatesUseCase {

    @Override
    public DistanceInKilometers calculate(GeoCoordinates geoCoordinatesA, GeoCoordinates geoCoordinatesB) {
        double latA = geoCoordinatesA.getX().doubleValue();
        double lonA = geoCoordinatesA.getY().doubleValue();
        double latB = geoCoordinatesB.getX().doubleValue();
        double lonB = geoCoordinatesB.getY().doubleValue();
        return DistanceInKilometers.of(distance(latA, lonA, latB, lonB, "K"));
    }

    private double distance(double latA, double lonA, double latB, double lonB, String unit) {
        if ((latA == latB) && (lonA == lonB)) {
            return 0;
        } else {
            double theta = lonA - lonB;
            double dist = Math.sin(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB)) + Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
}
