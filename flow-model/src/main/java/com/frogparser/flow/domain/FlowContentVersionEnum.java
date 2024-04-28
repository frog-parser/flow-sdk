package com.frogparser.flow.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FlowContentVersionEnum {

    V1_0_0_0("1.0.0.0");

    private final String version;

    FlowContentVersionEnum(String version) {
        this.version = version;
    }

    @JsonValue
    public String getVersion() {
        return version;
    }
}
