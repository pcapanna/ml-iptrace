package com.ml.iptrace.infrastructure.in_adapter.web.trace_ip.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "IP INVALIDA")
public class UnknownIpException extends Exception {
    String message;
}