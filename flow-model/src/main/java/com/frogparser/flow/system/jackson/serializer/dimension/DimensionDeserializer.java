package com.frogparser.flow.system.jackson.serializer.dimension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.openqa.selenium.Dimension;

import java.io.IOException;

public class DimensionDeserializer extends JsonDeserializer<Dimension> {

    @Override
    public Dimension deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        int height = 0;
        int width = 0;

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            final String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();

            if ("height".equals(fieldName)) {
                height = jsonParser.getIntValue();
            } else if ("width".equals(fieldName)) {
                width = jsonParser.getIntValue();
            }

        }

        return new Dimension(width, height);
    }

}