package com.frogparser.flow.domain.command.function.family.compare.less_or_equals;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LessOrEqualsVariableType {

    BIG_DECIMAL("BigDecimal"),
    INTEGER("Integer"),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime"),
    LONG("Long"),
    STRING("String"),
    URL("Url");

    private final String jsonValue;

    LessOrEqualsVariableType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }

}
