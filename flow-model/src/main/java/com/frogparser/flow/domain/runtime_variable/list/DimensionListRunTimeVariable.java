package com.frogparser.flow.domain.runtime_variable.list;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionDeserializer;
import com.frogparser.flow.system.jackson.serializer.dimension.DimensionSerializer;

import java.awt.*;
import java.util.List;

public class DimensionListRunTimeVariable extends AbstractListRunTimeVariable<Dimension> {

    public DimensionListRunTimeVariable() {
        super();
    }

    public DimensionListRunTimeVariable(List<Dimension> value) {
        super(value);
    }

    @JsonSerialize(contentUsing = DimensionSerializer.class)
    @JsonDeserialize(contentUsing = DimensionDeserializer.class)
    @Override
    public List<Dimension> getValue() {
        return super.getValue();
    }
}
