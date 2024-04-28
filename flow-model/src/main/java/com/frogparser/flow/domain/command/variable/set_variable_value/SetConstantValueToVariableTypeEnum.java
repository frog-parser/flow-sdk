package com.frogparser.flow.domain.command.variable.set_variable_value;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SetConstantValueToVariableTypeEnum {

    BIG_DECIMAL("BigDecimal"),
    BIG_DECIMAL_LIST("BigDecimalList"),
    BOOLEAN("Boolean"),
    BOOLEAN_LIST("BooleanList"),
    DIMENSION("Dimension"),
    INTEGER("Integer"),
    INTEGER_LIST("IntegerList"),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_LIST("LocalDateList"),
    LOCAL_DATE_TIME("LocalDateTime"),
    LOCAL_DATE_TIME_LIST("LocalDateTimeList"),
    LONG("Long"),
    LONG_LIST("LongList"),
    STRING("String"),
    STRING_LIST("StringList"),
    URL("Url"),
    URL_LIST("UrlList");

    private final String jsonValue;

    SetConstantValueToVariableTypeEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
