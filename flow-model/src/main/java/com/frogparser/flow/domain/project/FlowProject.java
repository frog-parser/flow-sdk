package com.frogparser.flow.domain.project;

import com.frogparser.flow.domain.Flow;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FlowProject {

    private Long id;
    private String name;
    private String description;
    private List<Flow> flows;

    @NotNull
    public Long getId() {
        return id;
    }

    public FlowProject setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public FlowProject setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FlowProject setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public FlowProject setFlows(List<Flow> flows) {
        this.flows = flows;
        return this;
    }
}
