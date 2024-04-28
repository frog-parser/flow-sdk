package com.frogparser.flow.domain.runtime_variable.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class LocalDateTimeListRunTimeVariable extends AbstractListRunTimeVariable<LocalDateTime> {

    public LocalDateTimeListRunTimeVariable() {
        super();
    }

    public LocalDateTimeListRunTimeVariable(List<LocalDateTime> value) {
        super(value);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(contentUsing = LocalDateTimeSerializer.class)
    @JsonDeserialize(contentUsing = LocalDateTimeDeserializer.class)
    @Override
    public List<LocalDateTime> getValue() {
        return super.getValue();
    }
}
