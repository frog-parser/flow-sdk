package com.frogparser.flow.domain.command.function.family.string;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class StrLenFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable parameter;
    private FlowVariable result;

    @Valid
    @NotNull
    public FlowVariable getParameter() {
        return parameter;
    }

    public StrLenFunctionCommand setParameter(FlowVariable parameter) {
        this.parameter = parameter;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public StrLenFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
