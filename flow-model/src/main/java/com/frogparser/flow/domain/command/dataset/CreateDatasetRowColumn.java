package com.frogparser.flow.domain.command.dataset;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateDatasetRowColumn {

    private String name;
    private FlowVariable columnVariable;

    @NotBlank
    public String getName() {
        return name;
    }

    public CreateDatasetRowColumn setName(String name) {
        this.name = name;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getColumnVariable() {
        return columnVariable;
    }

    public CreateDatasetRowColumn setColumnVariable(FlowVariable columnVariable) {
        this.columnVariable = columnVariable;
        return this;
    }
}
