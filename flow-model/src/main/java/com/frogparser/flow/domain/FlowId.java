package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotNull;

public class FlowId {

    private Long id;

    public FlowId() {

    }

    public FlowId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowId setId(Long id) {
        this.id = id;
        return this;
    }
}
