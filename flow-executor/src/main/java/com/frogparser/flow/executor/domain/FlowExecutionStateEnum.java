package com.frogparser.flow.executor.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FlowExecutionStateEnum {

    INTERRUPTED("Interrupted"),
    CANCELED("Canceled"),
    FAILED("Failed"),
    FINISHED("Finished"),
    PENDING("Pending"),
    RUNNING("Running"),
    STARTED("Started");

    private final String value;

    FlowExecutionStateEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
