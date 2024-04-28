package com.frogparser.flow.system.jackson.serializer.rectangle;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.openqa.selenium.Rectangle;

import java.io.IOException;

public class RectangleDeserializer extends JsonDeserializer<Rectangle> {

    @Override
    public Rectangle deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        int x = 0;
        int y = 0;
        int height = 0;
        int width = 0;

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();

            if ("x".equals(fieldName)) {
                x = jsonParser.getIntValue();
            } else if ("y".equals(fieldName)) {
                y = jsonParser.getIntValue();
            } else if ("height".equals(fieldName)) {
                height = jsonParser.getIntValue();
            } else if ("width".equals(fieldName)) {
                width = jsonParser.getIntValue();
            }
        }

        return new Rectangle(x, y, height, width);
    }

}