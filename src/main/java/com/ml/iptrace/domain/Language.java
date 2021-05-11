package com.ml.iptrace.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;


@NonNull
@Value
@AllArgsConstructor
public class Language {

    private final LanguageCode code;
    private final String name;

}
