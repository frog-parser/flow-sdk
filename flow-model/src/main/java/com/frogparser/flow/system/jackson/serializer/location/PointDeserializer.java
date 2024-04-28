package com.frogparser.flow.system.jackson.serializer.location;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.openqa.selenium.Point;

import java.io.IOException;

public class PointDeserializer extends JsonDeserializer<Point> {

    @Override
    public Point deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        int y = 0;
        int x = 0;

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            final String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();

            if ("x".equals(fieldName)) {
                y = jsonParser.getIntValue();
            } else if ("y".equals(fieldName)) {
                x = jsonParser.getIntValue();
            }

        }

        return new Point(x, y);
    }

}