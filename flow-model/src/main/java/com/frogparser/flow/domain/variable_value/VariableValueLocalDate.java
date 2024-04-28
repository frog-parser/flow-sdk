package com.frogparser.flow.domain.variable_value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VariableValueLocalDate extends AbstractVariableValue<LocalDate> {

    public VariableValueLocalDate() {
        super();
    }

    public VariableValueLocalDate(LocalDate value) {
        super(value);
    }

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }
}
