package com.ml.iptrace.application.in_port.trace_stats.get_distance_stats;


import com.ml.iptrace.domain.DistanceInKilometers;

import java.util.Optional;

public interface GetTraceDistanceStatsUseCase {


    Optional<DistanceInKilometers> findLongest();

    Optional<DistanceInKilometers> findShortest();

    Optional<DistanceInKilometers> findAverage();

}