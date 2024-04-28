package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class LocalDateRunTimeVariable extends AbstractRunTimeVariable<LocalDate> {

    public LocalDateRunTimeVariable() {
        super();
    }

    public LocalDateRunTimeVariable(LocalDate value) {
        super(value);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Override
    public LocalDate getValue() {
        return super.getValue();
    }
}
