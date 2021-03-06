package com.ml.iptrace.application.out_port;

import com.ml.iptrace.domain.DistanceInKilometers;
import org.springframework.scheduling.annotation.Async;

public interface RegisterTraceDistanceForStatsPort {
    @Async
    void register(DistanceInKilometers distance);

}
