package com.ml.iptrace.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Positive;

@Value
public class DistanceInKilometers {

    public static DistanceInKilometers ZERO = DistanceInKilometers.of(0d);

    @NonNull
    @Positive
    @JsonUnwrapped
    private final Double amount;

    public static DistanceInKilometers of(double value) {
        return new DistanceInKilometers(Double.valueOf(value));
    }

    public double toDouble() { return this.getAmount(); }

    public String toString(){
        return String.format("%.2f", this.amount) + " km";
    }

}
