package com.frogparser.flow.domain.runtime_variable.list;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.rectangle.RectangleDeserializer;
import com.frogparser.flow.system.jackson.serializer.rectangle.RectangleSerializer;
import org.openqa.selenium.Rectangle;

import java.util.List;

public class RectangleListRunTimeVariable extends AbstractListRunTimeVariable<Rectangle> {

    public RectangleListRunTimeVariable() {
        super();
    }

    public RectangleListRunTimeVariable(List<Rectangle> value) {
        super(value);
    }

    @JsonSerialize(contentUsing = RectangleSerializer.class)
    @JsonDeserialize(contentUsing = RectangleDeserializer.class)
    @Override
    public List<Rectangle> getValue() {
        return super.getValue();
    }
}
