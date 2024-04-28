package com.frogparser.flow.domain.variable_value.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class VariableValueLocalDateList extends AbstractVariableValueList<LocalDate> {

    public VariableValueLocalDateList() {
        super();
    }

    public VariableValueLocalDateList(List<LocalDate> value) {
        super(value);
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(contentUsing = LocalDateSerializer.class)
    @JsonDeserialize(contentUsing = LocalDateDeserializer.class)
    @Override
    public @NotNull List<@Valid @NotNull LocalDate> getValue() {
        return super.getValue();
    }

}
