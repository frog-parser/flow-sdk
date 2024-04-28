package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionDeserializer;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionSerializer;
import org.openqa.selenium.Dimension;

public class DimensionRunTimeVariable extends AbstractRunTimeVariable<Dimension> {

    public DimensionRunTimeVariable() {
        super();
    }

    public DimensionRunTimeVariable(Dimension value) {
        super(value);
    }

    @JsonSerialize(using = DimensionSerializer.class)
    @JsonDeserialize(using = DimensionDeserializer.class)
    @Override
    public Dimension getValue() {
        return super.getValue();
    }
}
