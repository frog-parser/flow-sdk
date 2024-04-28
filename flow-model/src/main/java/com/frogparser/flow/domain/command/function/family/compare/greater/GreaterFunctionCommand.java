package com.frogparser.flow.domain.command.function.family.compare.greater;

import com.frogparser.flow.domain.command.function.family.compare.AbstractCompareFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class GreaterFunctionCommand extends AbstractCompareFunctionCommand {

    private GreaterVariableType variableType;
    private FlowVariable parameterOne;
    private FlowVariable parameterTwo;

    @NotNull
    public GreaterVariableType getVariableType() {
        return variableType;
    }

    public GreaterFunctionCommand setVariableType(GreaterVariableType variableType) {
        this.variableType = variableType;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterOne() {
        return parameterOne;
    }

    public GreaterFunctionCommand setParameterOne(FlowVariable parameterOne) {
        this.parameterOne = parameterOne;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterTwo() {
        return parameterTwo;
    }

    public GreaterFunctionCommand setParameterTwo(FlowVariable parameterTwo) {
        this.parameterTwo = parameterTwo;
        return this;
    }
}
