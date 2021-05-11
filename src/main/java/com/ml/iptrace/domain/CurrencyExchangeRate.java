package com.ml.iptrace.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeRate {
    @NotNull
    CurrencyCode currencyCode;
    Optional<ExchangeRate> exchangeRate;
}
