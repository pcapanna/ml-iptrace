package com.ml.iptrace.application.port.in.country.list_current_times;

import com.ml.iptrace.application.UseCase;
import com.ml.iptrace.domain.Country;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class ListCountryCurrentTimesService implements ListCountryCurrentTimesUseCase {

    public Collection<ZonedDateTime> of(Country country) {
        return country.getTimeZones().stream()
                .map(this::getCurrentTime)
                .collect(Collectors.toList());
    }

    private ZonedDateTime getCurrentTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

}
