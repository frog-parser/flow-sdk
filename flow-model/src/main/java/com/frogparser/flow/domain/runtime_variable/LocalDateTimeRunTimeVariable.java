package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class LocalDateTimeRunTimeVariable extends AbstractRunTimeVariable<LocalDateTime> {

    public LocalDateTimeRunTimeVariable() {
        super();
    }

    public LocalDateTimeRunTimeVariable(LocalDateTime value) {
        super(value);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Override
    public LocalDateTime getValue() {
        return super.getValue();
    }
}
