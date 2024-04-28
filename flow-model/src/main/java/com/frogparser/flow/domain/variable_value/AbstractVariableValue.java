package com.frogparser.flow.domain.variable_value;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.frogparser.flow.domain.variable_value.list.*;
import com.frogparser.flow.domain.variable_value.list.*;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VariableValueBigDecimal.class, name = "VariableValueBigDecimal"),
        @JsonSubTypes.Type(value = VariableValueBigDecimalList.class, name = "VariableValueBigDecimalList"),
        @JsonSubTypes.Type(value = VariableValueBoolean.class, name = "VariableValueBoolean"),
        @JsonSubTypes.Type(value = VariableValueBooleanList.class, name = "VariableValueBooleanList"),
        @JsonSubTypes.Type(value = VariableValueDimension.class, name = "VariableValueDimension"),
        @JsonSubTypes.Type(value = VariableValueInteger.class, name = "VariableValueInteger"),
        @JsonSubTypes.Type(value = VariableValueIntegerList.class, name = "VariableValueIntegerList"),
        @JsonSubTypes.Type(value = VariableValueLocalDate.class, name = "VariableValueLocalDate"),
        @JsonSubTypes.Type(value = VariableValueLocalDateList.class, name = "VariableValueLocalDateList"),
        @JsonSubTypes.Type(value = VariableValueLocalDateTime.class, name = "VariableValueLocalDateTime"),
        @JsonSubTypes.Type(value = VariableValueLocalDateTimeList.class, name = "VariableValueLocalDateTimeList"),
        @JsonSubTypes.Type(value = VariableValueLong.class, name = "VariableValueLong"),
        @JsonSubTypes.Type(value = VariableValueLongList.class, name = "VariableValueLongList"),
        @JsonSubTypes.Type(value = VariableValueString.class, name = "VariableValueString"),
        @JsonSubTypes.Type(value = VariableValueStringList.class, name = "VariableValueStringList"),
        @JsonSubTypes.Type(value = VariableValueUrl.class, name = "VariableValueUrl"),
        @JsonSubTypes.Type(value = VariableValueUrlList.class, name = "VariableValueUrlList")
})
public abstract class AbstractVariableValue<T> {

    private T value;

    public AbstractVariableValue() {

    }

    public AbstractVariableValue(T value) {
        this.value = value;
    }

    @NotNull
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
