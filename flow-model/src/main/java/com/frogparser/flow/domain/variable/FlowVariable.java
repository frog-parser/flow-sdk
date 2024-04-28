package com.frogparser.flow.domain.variable;

import jakarta.validation.constraints.NotBlank;

public class FlowVariable {

    private String name;

    public FlowVariable() {

    }

    public FlowVariable(String name) {
        this.name = name;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
