package com.frogparser.flow.executor.domain;

import com.frogparser.flow.domain.runtime_variable.RunTimeVariableType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RunTimeVariableHeader {

    private Long id;
    private String name;
    private RunTimeVariableType variableType;

    public RunTimeVariableHeader() {

    }

    public RunTimeVariableHeader(Long id, String name, RunTimeVariableType variableType) {
        this.id = id;
        this.name = name;
        this.variableType = variableType;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public RunTimeVariableHeader setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public RunTimeVariableHeader setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public RunTimeVariableType getVariableType() {
        return variableType;
    }

    public RunTimeVariableHeader setVariableType(RunTimeVariableType variableType) {
        this.variableType = variableType;
        return this;
    }
}
