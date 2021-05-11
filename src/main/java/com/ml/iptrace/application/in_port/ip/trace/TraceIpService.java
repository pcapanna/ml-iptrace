package com.ml.iptrace.application.in_port.ip.trace;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.application.in_port.country.find_by_ip.FindCountryByIpUseCase;
import com.ml.iptrace.application.in_port.country.list_current_times.ListCountryCurrentTimesUseCase;
import com.ml.iptrace.application.in_port.currency_exchange.find_exchange_rate_in_eur.FindCurrencyExchangeRateInEur;
import com.ml.iptrace.application.in_port.geo_distance.calculate_between_country_to_bsas.CalculateDistanceBetweenCountryToBsAsUseCase;
import com.ml.iptrace.application.out_port.RegisterTraceDistanceForStatsPort;
import com.ml.iptrace.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional
public class TraceIpService implements TraceIpUseCase {

    private final FindCountryByIpUseCase findCountryByIp;
    private final CalculateDistanceBetweenCountryToBsAsUseCase calculateDistanceBetweenCountryToBsAsUseCase;
    private final ListCountryCurrentTimesUseCase listCountryCurrentTimes;
    private final FindCurrencyExchangeRateInEur findCurrencyExchangeRateInEur;
    private final RegisterTraceDistanceForStatsPort registerTraceDistanceForStatsPort;

    public TraceIpResponse trace(InetAddress inetAddress) {
        Country country = findCountryByIp.find(inetAddress);
        Collection<ZonedDateTime> currentCountryTimes = listCountryCurrentTimes.of(country);
        DistanceInKilometers distance = calculateDistanceBetweenCountryToBsAsUseCase.from(country);
        Map<CurrencyCode, Optional<ExchangeRate>> countryCurrenciesExchangeRates =
                findCurrencyExchangeRateInEur.forCurrencies(country.getCurrencies().stream()
                .map(Currency::getCode).collect(Collectors.toList()));
        registerTraceDistanceForStatsPort.register(distance);
        return new TraceIpResponse(country, currentCountryTimes, countryCurrenciesExchangeRates, distance);
    }
}
