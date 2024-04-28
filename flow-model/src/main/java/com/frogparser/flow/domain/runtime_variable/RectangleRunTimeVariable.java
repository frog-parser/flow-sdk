package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.rectangle.RectangleDeserializer;
import com.frogparser.flow.system.jackson.serializer.rectangle.RectangleSerializer;
import org.openqa.selenium.Rectangle;

public class RectangleRunTimeVariable extends AbstractRunTimeVariable<Rectangle> {

    public RectangleRunTimeVariable() {
        super();
    }

    public RectangleRunTimeVariable(Rectangle value) {
        super(value);
    }

    @JsonSerialize(using = RectangleSerializer.class)
    @JsonDeserialize(using = RectangleDeserializer.class)
    @Override
    public Rectangle getValue() {
        return super.getValue();
    }
}
