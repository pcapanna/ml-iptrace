package com.ml.iptrace.application.port.out;

import com.ml.iptrace.domain.DistanceInKilometers;

import java.util.Optional;

public interface ResolveTraceDistanceStatPort {
    Optional<DistanceInKilometers> findLongestDistance();

    Optional<DistanceInKilometers> findShortestDistance();

    Optional<DistanceInKilometers> findAverageDistance();

}
