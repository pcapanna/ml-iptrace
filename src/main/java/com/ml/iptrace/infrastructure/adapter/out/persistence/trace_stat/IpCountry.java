package com.ml.iptrace.infrastructure.adapter.out.persistence.trace_stat;

import com.ml.iptrace.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IpCountry {
    @Id
    String ip;
    Country country;
}
