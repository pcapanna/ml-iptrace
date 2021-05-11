package com.ml.iptrace.application.in_port.trace_stats.find_distance_stat;


import com.ml.iptrace.domain.DistanceInKilometers;

import java.util.Optional;

public interface FindTraceDistanceStatUseCase {


    Optional<DistanceInKilometers> findLongest();

    Optional<DistanceInKilometers> findShortest();

    Optional<DistanceInKilometers> findAverage();

}