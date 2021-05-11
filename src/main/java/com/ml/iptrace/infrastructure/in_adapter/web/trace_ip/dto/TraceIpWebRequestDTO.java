package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraceIpWebRequestDTO {
    @NotNull
    String ip;
}
