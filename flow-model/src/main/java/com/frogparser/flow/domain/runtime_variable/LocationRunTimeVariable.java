package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.location.PointDeserializer;
import com.frogparser.flow.system.jackson.serializer.location.PointSerializer;
import org.openqa.selenium.Point;

public class LocationRunTimeVariable extends AbstractRunTimeVariable<Point> {

    public LocationRunTimeVariable() {
        super();
    }

    public LocationRunTimeVariable(Point value) {
        super(value);
    }

    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    @Override
    public Point getValue() {
        return super.getValue();
    }
}
