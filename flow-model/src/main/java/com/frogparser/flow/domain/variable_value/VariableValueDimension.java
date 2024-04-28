package com.frogparser.flow.domain.variable_value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionDeserializer;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionSerializer;
import jakarta.validation.constraints.NotNull;
import org.openqa.selenium.Dimension;

public class VariableValueDimension extends AbstractVariableValue<Dimension> {

    public VariableValueDimension() {
        super();
    }

    public VariableValueDimension(Dimension value) {
        super(value);
    }

    @NotNull
    @JsonSerialize(using = DimensionSerializer.class)
    @JsonDeserialize(using = DimensionDeserializer.class)
    @Override
    public Dimension getValue() {
        return super.getValue();
    }
}
