package com.ml.iptrace.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class Currency {

    @NotNull private final CurrencyCode code;
    @NotNull private final String name;
    @NotNull private final String symbol;

}
