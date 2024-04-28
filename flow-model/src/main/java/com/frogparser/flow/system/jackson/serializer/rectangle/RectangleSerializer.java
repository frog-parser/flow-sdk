package com.frogparser.flow.system.jackson.serializer.rectangle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.openqa.selenium.Rectangle;

import java.io.IOException;

public class RectangleSerializer extends JsonSerializer<Rectangle> {

    @Override
    public void serialize(Rectangle rectangle, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("x", rectangle.getX());
        jsonGenerator.writeNumberField("y", rectangle.getY());
        jsonGenerator.writeNumberField("height", rectangle.getHeight());
        jsonGenerator.writeNumberField("width", rectangle.getWidth());
        jsonGenerator.writeEndObject();
    }

}