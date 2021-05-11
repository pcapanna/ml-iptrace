package com.ml.iptrace.infrastructure.in_adapter.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExceptionDTO {
    private String name;
    private String message;
}