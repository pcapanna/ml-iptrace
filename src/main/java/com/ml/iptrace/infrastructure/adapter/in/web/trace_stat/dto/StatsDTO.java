package com.ml.iptrace.infrastructure.adapter.in.web.trace_stat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ml.iptrace.application.port.in.trace_stats.get_distance_stats.TraceDistanceStatsResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter(AccessLevel.PRIVATE)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDTO {
    String shortestDistance;
    String longestDistance;
    String averageDistance;

    public StatsDTO(TraceDistanceStatsResponse traceDistanceStatsResponse) {
        this.shortestDistance =
                traceDistanceStatsResponse.getShortestDistance()
                        .map(distance -> distance.toString())
                        .orElse("N/A");
        this.longestDistance =
                traceDistanceStatsResponse.getLongestDistance()
                        .map(distance -> distance.toString())
                        .orElse("N/A");
        this.averageDistance =
                traceDistanceStatsResponse.getAverageDistance()
                        .map(distance -> distance.toString())
                        .orElse("N/A");
    }
}
