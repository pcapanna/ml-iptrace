package com.ml.iptrace.infrastructure.out_adapter.persistence.trace_stat;

import com.ml.iptrace.application.out_port.RegisterTraceDistanceForStatsPort;
import com.ml.iptrace.application.out_port.ResolveTraceDistanceStatPort;
import com.ml.iptrace.domain.DistanceInKilometers;
import com.ml.iptrace.infrastructure.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class LockStrategyDistanceStatPersistenceAdapter
        implements RegisterTraceDistanceForStatsPort, ResolveTraceDistanceStatPort {

    private Optional<DistanceInKilometers> minDistance = Optional.empty();
    private Optional<DistanceInKilometers> maxDistance = Optional.empty();
    private Integer registeredDistances = 0;
    private DistanceInKilometers totalDistances = DistanceInKilometers.of(0.0);

    @Override
    public Optional<DistanceInKilometers> findLongestDistance() {
        return this.minDistance;
    }

    @Override
    public Optional<DistanceInKilometers> findShortestDistance() {
        return this.maxDistance;
    }

    @Override
    public synchronized Optional<DistanceInKilometers> findAverageDistance() {
        if (registeredDistances == 0) {
            return Optional.empty();
        }
        DistanceInKilometers averageDistance = DistanceInKilometers.of(
                this.totalDistances.toDouble() / this.registeredDistances);
        return Optional.of(averageDistance);
    }

    @Override
    public synchronized void register(DistanceInKilometers distance) {
        if (this.registeredDistances == 0) {
            this.minDistance = Optional.of(distance);
            this.maxDistance = Optional.of(distance);
        } else {
            if (this.minDistance.get().toDouble() > distance.toDouble()) {
                this.minDistance = Optional.of(distance);
            } else if (this.maxDistance.get().toDouble() < distance.toDouble()) {
                this.maxDistance = Optional.of(distance);
            }
        }
        this.registeredDistances++;
        this.totalDistances = DistanceInKilometers.of(this.totalDistances.toDouble()+distance.toDouble());
    }

}
