package com.ml.iptrace.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;

@Value
public class LanguageCode {

    @NonNull
    @JsonValue
    private final String code;

    public static LanguageCode of(String value) {
        return new LanguageCode(String.valueOf(value));
    }

    public String toString() {
        return this.getCode();
    }

}
