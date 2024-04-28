package com.frogparser.flow.domain.variable_value.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class VariableValueLocalDateTimeList extends AbstractVariableValueList<LocalDateTime> {

    public VariableValueLocalDateTimeList() {
        super();
    }

    public VariableValueLocalDateTimeList(List<LocalDateTime> value) {
        super(value);
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(contentUsing = LocalDateTimeSerializer.class)
    @JsonDeserialize(contentUsing = LocalDateTimeDeserializer.class)
    @Override
    public @NotNull List<@Valid @NotNull LocalDateTime> getValue() {
        return super.getValue();
    }
}
