package com.frogparser.flow.domain.command.dataset;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateDatasetRowCommand extends AbstractCommand {

    private FlowVariable variable;
    private List<CreateDatasetRowColumn> columns;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public CreateDatasetRowCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull CreateDatasetRowColumn> getColumns() {
        return columns;
    }

    public CreateDatasetRowCommand setColumns(List<CreateDatasetRowColumn> columns) {
        this.columns = columns;
        return this;
    }
}
