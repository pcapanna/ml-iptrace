package com.ml.iptrace.application.in_port.trace_stats.find_distance_stat;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.out_port.ResolveTraceDistanceStatPort;
import com.ml.iptrace.domain.DistanceInKilometers;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FindTraceDistanceStat implements FindTraceDistanceStatUseCase{

    private final ResolveTraceDistanceStatPort resolveTraceDistanceStatPort;

    public Optional<DistanceInKilometers> findLongest() {
        return resolveTraceDistanceStatPort.findLongestDistance();
    }
    public Optional<DistanceInKilometers> findShortest() {
        return resolveTraceDistanceStatPort.findShortestDistance();
    }
    public Optional<DistanceInKilometers> findAverage() {
        return resolveTraceDistanceStatPort.findAverageDistance();
    }
}
