package com.frogparser.flow.domain.command.function.family.logical;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class NegateFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable parameter;

    @Valid
    @NotNull
    public FlowVariable getParameter() {
        return parameter;
    }

    public NegateFunctionCommand setParameter(FlowVariable parameter) {
        this.parameter = parameter;
        return this;
    }
}
