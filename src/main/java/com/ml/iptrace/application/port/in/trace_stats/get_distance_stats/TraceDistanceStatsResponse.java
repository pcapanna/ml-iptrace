package com.ml.iptrace.application.port.in.trace_stats.get_distance_stats;

import com.ml.iptrace.domain.DistanceInKilometers;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class TraceDistanceStatsResponse {

    private final Optional<DistanceInKilometers> shortestDistance;
    private final Optional<DistanceInKilometers> longestDistance;
    private final Optional<DistanceInKilometers> averageDistance;

}
