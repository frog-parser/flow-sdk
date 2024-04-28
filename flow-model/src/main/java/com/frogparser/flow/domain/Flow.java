package com.frogparser.flow.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Flow {

    private Long id;
    private String name;
    private String description;
    private FlowContent content;

    @NotNull
    public Long getId() {
        return id;
    }

    public Flow setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public Flow setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Flow setDescription(String description) {
        this.description = description;
        return this;
    }

    @Valid
    @NotNull
    public FlowContent getContent() {
        return content;
    }

    public Flow setContent(FlowContent content) {
        this.content = content;
        return this;
    }
}
