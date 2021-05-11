package com.ml.iptrace.infrastructure.in_adapter.web.trace_stat;

import com.ml.iptrace.application.in_port.trace_stats.get_distance_stats.GetTraceDistanceStats;
import com.ml.iptrace.infrastructure.WebAdapter;
import com.ml.iptrace.infrastructure.in_adapter.web.trace_stat.dto.StatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TraceStatsController {

    private final GetTraceDistanceStats getTraceDistanceStats;

    @GetMapping(path = "/stats")
    @ResponseStatus(HttpStatus.OK)
    StatsDTO stats() {
        return new StatsDTO(getTraceDistanceStats.get());
    }

}
