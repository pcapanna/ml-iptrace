package com.ml.iptrace.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class JsonZonedTimeSerializer extends JsonSerializer<ZonedDateTime> {
    // ISO 8601
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss z");

    @Override
    public void serialize(ZonedDateTime time, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        String formattedDate = time.format(formatter);
        gen.writeString(formattedDate);
    }
}
