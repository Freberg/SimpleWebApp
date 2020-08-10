package com.freberg.app.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CommaSeparatedCollectionDeserializer extends JsonDeserializer<Collection<String>> {

    private static final String DELIMITER = ",";

    @Override
    public Collection<String> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String text = parser.getText();
        if (text == null) {
            return Collections.emptySet();
        }
        return Arrays.stream(text.split(DELIMITER))
                     .map(String::trim)
                     .collect(Collectors.toSet());
    }
}
