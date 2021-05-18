package com.ml.iptrace.infrastructure.adapter.in.web.trace_ip.dto;

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
