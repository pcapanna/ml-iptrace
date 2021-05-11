package com.ml.iptrace.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

@Value
public class IpAddress {

    @NonNull
    @JsonUnwrapped
    private final String value;

    public static IpAddress of(String value) {
        return new IpAddress(value);
    }

    public String toString() {
        return this.getValue();
    }

}