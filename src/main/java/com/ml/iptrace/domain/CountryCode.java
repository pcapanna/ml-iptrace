package com.ml.iptrace.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

@Value
public class CountryCode {

    @NonNull
    @JsonUnwrapped
    private final String code;

    public static CountryCode of(String value) {
        return new CountryCode(String.valueOf(value));
    }

    public String toString() {
        return this.getCode();
    }

}
