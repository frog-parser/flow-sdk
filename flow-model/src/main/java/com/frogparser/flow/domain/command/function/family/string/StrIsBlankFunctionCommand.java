package com.frogparser.flow.domain.command.function.family.string;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class StrIsBlankFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable parameter;
    private FlowVariable result;

    @Valid
    @NotNull
    public FlowVariable getParameter() {
        return parameter;
    }

    public StrIsBlankFunctionCommand setParameter(FlowVariable parameter) {
        this.parameter = parameter;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public StrIsBlankFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
