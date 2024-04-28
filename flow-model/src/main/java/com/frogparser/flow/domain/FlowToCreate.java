package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlowToCreate {

    private Long projectId;
    private String name;
    private String description;

    @NotNull
    public Long getProjectId() {
        return projectId;
    }

    public FlowToCreate setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public FlowToCreate setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FlowToCreate setDescription(String description) {
        this.description = description;
        return this;
    }
}
