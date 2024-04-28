package com.frogparser.flow.domain.dataset_metadata;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DatasetMetadataColumnTypeEnum {
    BIG_DECIMAL("BigDecimal"),
    BOOLEAN("Boolean"),
    INTEGER("Integer"),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime"),
    LONG("Long"),
    STRING("String"),
    URL("Url");

    private final String jsonValue;

    DatasetMetadataColumnTypeEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }
}
