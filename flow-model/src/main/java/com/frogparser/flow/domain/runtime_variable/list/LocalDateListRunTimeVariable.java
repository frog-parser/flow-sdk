package com.frogparser.flow.domain.runtime_variable.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class LocalDateListRunTimeVariable extends AbstractListRunTimeVariable<LocalDate> {

    public LocalDateListRunTimeVariable() {
        super();
    }

    public LocalDateListRunTimeVariable(List<LocalDate> value) {
        super(value);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(contentUsing = LocalDateSerializer.class)
    @JsonDeserialize(contentUsing = LocalDateDeserializer.class)
    @Override
    public List<LocalDate> getValue() {
        return super.getValue();
    }
}
