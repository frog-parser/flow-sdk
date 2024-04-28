package com.frogparser.flow.domain.command.function.family.compare.greater;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GreaterVariableType {

    BIG_DECIMAL("BigDecimal"),
    INTEGER("Integer"),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime"),
    LONG("Long"),
    STRING("String"),
    URL("Url");

    private final String jsonValue;

    GreaterVariableType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
