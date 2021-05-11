package com.ml.iptrace.application.out_port;

import com.ml.iptrace.domain.DistanceInKilometers;

public interface RegisterTraceDistanceForStatsPort {

    void register(DistanceInKilometers distance);

}
