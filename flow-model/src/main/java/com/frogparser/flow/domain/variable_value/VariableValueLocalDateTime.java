package com.frogparser.flow.domain.variable_value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VariableValueLocalDateTime extends AbstractVariableValue<LocalDateTime> {

    public VariableValueLocalDateTime() {
        super();
    }

    public VariableValueLocalDateTime(LocalDateTime value) {
        super(value);
    }

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Override
    public LocalDateTime getValue() {
        return super.getValue();
    }

}
