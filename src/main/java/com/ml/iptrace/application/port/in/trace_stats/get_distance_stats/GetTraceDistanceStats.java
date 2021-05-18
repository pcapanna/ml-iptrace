package com.ml.iptrace.application.port.in.trace_stats.get_distance_stats;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.port.in.trace_stats.find_distance_stat.FindTraceDistanceStat;
import com.ml.iptrace.domain.DistanceInKilometers;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetTraceDistanceStats {

    private final FindTraceDistanceStat findTraceDistanceStat;

    public TraceDistanceStatsResponse get() {
        Optional<DistanceInKilometers> shortestDistance = findTraceDistanceStat.findShortest();
        Optional<DistanceInKilometers> longestDistance = findTraceDistanceStat.findLongest();;
        Optional<DistanceInKilometers> averageDistance = findTraceDistanceStat.findAverage();;
        return new TraceDistanceStatsResponse(shortestDistance, longestDistance, averageDistance);
    }
}
