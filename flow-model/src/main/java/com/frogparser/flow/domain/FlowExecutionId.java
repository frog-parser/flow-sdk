package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotNull;

public class FlowExecutionId {

    private Long id;

    public FlowExecutionId() {

    }

    public FlowExecutionId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowExecutionId setId(Long id) {
        this.id = id;
        return this;
    }
}
