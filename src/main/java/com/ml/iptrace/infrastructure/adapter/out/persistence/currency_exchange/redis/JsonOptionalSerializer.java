package com.ml.iptrace.infrastructure.adapter.out.persistence.currency_exchange.redis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class JsonOptionalSerializer extends JsonSerializer<Optional<Object>> {

    @Override
    public void serialize(Optional<Object> optional, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        Object object = optional.orElse(null);
        gen.writeObject(object);
    }
}
