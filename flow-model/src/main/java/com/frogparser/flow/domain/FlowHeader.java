package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlowHeader {

    private Long id;
    private String name;
    private String description;

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowHeader setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public FlowHeader setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FlowHeader setDescription(String description) {
        this.description = description;
        return this;
    }
}
