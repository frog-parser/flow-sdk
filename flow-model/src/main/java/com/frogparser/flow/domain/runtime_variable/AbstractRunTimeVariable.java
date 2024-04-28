package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.frogparser.flow.domain.runtime_variable.list.*;
import com.frogparser.flow.domain.runtime_variable.list.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BigDecimalListRunTimeVariable.class, name = "BigDecimalListRunTimeVariable"),
        @JsonSubTypes.Type(value = BigDecimalRunTimeVariable.class, name = "BigDecimalRunTimeVariable"),
        @JsonSubTypes.Type(value = BooleanListRunTimeVariable.class, name = "BooleanListRunTimeVariable"),
        @JsonSubTypes.Type(value = BooleanRunTimeVariable.class, name = "BooleanRunTimeVariable"),
        @JsonSubTypes.Type(value = CustomWebElementListRunTimeVariable.class, name = "CustomWebElementListRunTimeVariable"),
        @JsonSubTypes.Type(value = CustomWebElementRunTimeVariable.class, name = "CustomWebElementRunTimeVariable"),
        @JsonSubTypes.Type(value = DatasetRunTimeVariable.class, name = "DatasetRunTimeVariable"),
        @JsonSubTypes.Type(value = DimensionListRunTimeVariable.class, name = "DimensionListRunTimeVariable"),
        @JsonSubTypes.Type(value = DimensionRunTimeVariable.class, name = "DimensionRunTimeVariable"),
        @JsonSubTypes.Type(value = IntegerListRunTimeVariable.class, name = "IntegerListRunTimeVariable"),
        @JsonSubTypes.Type(value = IntegerRunTimeVariable.class, name = "IntegerRunTimeVariable"),
        @JsonSubTypes.Type(value = LocalDateListRunTimeVariable.class, name = "LocalDateListRunTimeVariable"),
        @JsonSubTypes.Type(value = LocalDateRunTimeVariable.class, name = "LocalDateRunTimeVariable"),
        @JsonSubTypes.Type(value = LocalDateTimeListRunTimeVariable.class, name = "LocalDateTimeListRunTimeVariable"),
        @JsonSubTypes.Type(value = LocalDateTimeRunTimeVariable.class, name = "LocalDateTimeRunTimeVariable"),
        @JsonSubTypes.Type(value = LocationListRunTimeVariable.class, name = "LocationListRunTimeVariable"),
        @JsonSubTypes.Type(value = LocationRunTimeVariable.class, name = "LocationRunTimeVariable"),
        @JsonSubTypes.Type(value = LongListRunTimeVariable.class, name = "LongListRunTimeVariable"),
        @JsonSubTypes.Type(value = LongRunTimeVariable.class, name = "LongRunTimeVariable"),
        @JsonSubTypes.Type(value = RectangleListRunTimeVariable.class, name = "RectangleListRunTimeVariable"),
        @JsonSubTypes.Type(value = RectangleRunTimeVariable.class, name = "RectangleRunTimeVariable"),
        @JsonSubTypes.Type(value = StringListRunTimeVariable.class, name = "StringListRunTimeVariable"),
        @JsonSubTypes.Type(value = StringRunTimeVariable.class, name = "StringRunTimeVariable"),
        @JsonSubTypes.Type(value = UrlListRunTimeVariable.class, name = "UrlListRunTimeVariable"),
        @JsonSubTypes.Type(value = UrlRunTimeVariable.class, name = "UrlRunTimeVariable")
})
public abstract class AbstractRunTimeVariable<T> {

    private T value;

    public AbstractRunTimeVariable() {

    }

    public AbstractRunTimeVariable(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public AbstractRunTimeVariable<T> setValue(T value) {
        this.value = value;
        return this;
    }
}
