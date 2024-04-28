package com.frogparser.flow.domain.runtime_variable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.frogparser.flow.domain.runtime_variable.list.*;
import com.frogparser.flow.domain.runtime_variable.list.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public enum RunTimeVariableType {

    BIG_DECIMAL_LIST(BigDecimalListRunTimeVariable.class),
    BIG_DECIMAL(BigDecimalRunTimeVariable.class),
    BOOLEAN_LIST(BooleanListRunTimeVariable.class),
    BOOLEAN(BooleanRunTimeVariable.class),
    CUSTOM_WEB_ELEMENT_LIST(CustomWebElementListRunTimeVariable.class),
    CUSTOM_WEB_ELEMENT(CustomWebElementRunTimeVariable.class),
    DATASET(DatasetRunTimeVariable.class),
    DIMENSION_LIST(DimensionListRunTimeVariable.class),
    DIMENSION(DimensionRunTimeVariable.class),
    INTEGER_LIST(IntegerListRunTimeVariable.class),
    INTEGER(IntegerRunTimeVariable.class),
    LOCAL_DATE_LIST(LocalDateListRunTimeVariable.class),
    LOCAL_DATE(LocalDateRunTimeVariable.class),
    LOCAL_DATE_TIME_LIST(LocalDateTimeListRunTimeVariable.class),
    LOCAL_DATE_TIME(LocalDateTimeRunTimeVariable.class),
    LOCATION_LIST(LocationListRunTimeVariable.class),
    LOCATION(LocationRunTimeVariable.class),
    LONG_LIST(LongListRunTimeVariable.class),
    LONG(LongRunTimeVariable.class),
    RECTANGLE_LIST(RectangleListRunTimeVariable.class),
    RECTANGLE(RectangleRunTimeVariable.class),
    STRING_LIST(StringListRunTimeVariable.class),
    STRING(StringRunTimeVariable.class),
    URL_LIST(UrlListRunTimeVariable.class),
    URL(UrlRunTimeVariable.class);

    private final Class<? extends AbstractRunTimeVariable<?>> clazz;
    private final String value;

    private final static Map<Class<? extends AbstractRunTimeVariable<?>>, RunTimeVariableType> classToEnumMap = new LinkedHashMap<>();

    static {

        Arrays.stream(RunTimeVariableType.values()).forEach(runTimeVariableType -> {
            classToEnumMap.put(runTimeVariableType.getClazz(), runTimeVariableType);
        });

    }

    RunTimeVariableType(Class<? extends AbstractRunTimeVariable<?>> clazz) {
        this.clazz = clazz;
        this.value = clazz.getSimpleName();
    }

    public Class<? extends AbstractRunTimeVariable<?>> getClazz() {
        return clazz;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static Optional<RunTimeVariableType> getType(Class<? extends AbstractRunTimeVariable<?>> clazz) {
        return Optional.ofNullable(classToEnumMap.get(clazz));
    }

}
