package com.ml.iptrace.application.out_port;

import com.ml.iptrace.domain.DistanceInKilometers;

import java.util.Optional;

public interface ResolveTraceDistanceStatPort {
    Optional<DistanceInKilometers> findLongestDistance();

    Optional<DistanceInKilometers> findShortestDistance();

    Optional<DistanceInKilometers> findAverageDistance();

}
