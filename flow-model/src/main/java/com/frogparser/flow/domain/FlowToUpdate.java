package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlowToUpdate {

    private Long id;
    private String name;
    private String description;

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowToUpdate setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public FlowToUpdate setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FlowToUpdate setDescription(String description) {
        this.description = description;
        return this;
    }
}
