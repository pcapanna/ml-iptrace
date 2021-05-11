package com.ml.iptrace.application.in_port.ip.trace;

import com.ml.iptrace.domain.Country;
import com.ml.iptrace.domain.CurrencyCode;
import com.ml.iptrace.domain.DistanceInKilometers;
import com.ml.iptrace.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class TraceIpResponse {
    Country country;
    Collection<ZonedDateTime> currentCountryTimes;
    Map<CurrencyCode, Optional<ExchangeRate>> countryCurrenciesExchangeRates;
    DistanceInKilometers distanceInKilometers;
}