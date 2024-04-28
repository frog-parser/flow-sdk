package com.frogparser.flow.domain.command.function.family.string;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class StrContainsFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable parameterOne;
    private FlowVariable parameterTwo;

    private FlowVariable result;

    @Valid
    @NotNull
    public FlowVariable getParameterOne() {
        return parameterOne;
    }

    public StrContainsFunctionCommand setParameterOne(FlowVariable parameterOne) {
        this.parameterOne = parameterOne;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterTwo() {
        return parameterTwo;
    }

    public StrContainsFunctionCommand setParameterTwo(FlowVariable parameterTwo) {
        this.parameterTwo = parameterTwo;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public StrContainsFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
