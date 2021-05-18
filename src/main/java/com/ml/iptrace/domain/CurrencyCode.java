package com.ml.iptrace.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class CurrencyCode implements Serializable {

    @NonNull
    @JsonUnwrapped
    private final String code;

    public static CurrencyCode of(String value) {
        return new CurrencyCode(String.valueOf(value));
    }

    public String toString() {
        return this.getCode();
    }

}
