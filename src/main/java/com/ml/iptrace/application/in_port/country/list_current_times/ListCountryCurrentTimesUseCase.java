package com.ml.iptrace.application.in_port.country.list_current_times;

import com.ml.iptrace.domain.Country;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;

public interface ListCountryCurrentTimesUseCase {

    Collection<ZonedDateTime> of(Country country);

}
