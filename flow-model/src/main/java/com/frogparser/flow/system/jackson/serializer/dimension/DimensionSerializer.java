package com.frogparser.flow.system.jackson.serializer.dimension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.openqa.selenium.Dimension;

import java.io.IOException;

public class DimensionSerializer extends JsonSerializer<Dimension> {

    @Override
    public void serialize(Dimension dimension, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("width", dimension.width);
        jsonGenerator.writeNumberField("height", dimension.height);
        jsonGenerator.writeEndObject();
    }

}