package com.frogparser.flow.domain.command.function.family.logical;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public abstract class AbstractLogicalFunctionCommand extends AbstractFunctionCommand {

    private List<FlowVariable> parameters;
    private FlowVariable result;

    @Valid
    @NotNull
    public List<@Valid @NotNull FlowVariable> getParameters() {
        return parameters;
    }

    public AbstractLogicalFunctionCommand setParameters(List<FlowVariable> parameters) {
        this.parameters = parameters;
        return this;
    }

    public FlowVariable getResult() {
        return result;
    }

    public AbstractLogicalFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
