package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotNull;

public class FlowToDelete {

    private Long id;

    public FlowToDelete() {

    }

    public FlowToDelete(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowToDelete setId(Long id) {
        this.id = id;
        return this;
    }
}
