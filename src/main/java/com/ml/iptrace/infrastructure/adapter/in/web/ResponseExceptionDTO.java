package com.ml.iptrace.infrastructure.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExceptionDTO {
    private String name;
    private String message;
}