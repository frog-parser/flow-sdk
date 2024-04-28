package com.frogparser.flow.domain.command.function.family.compare.greater_or_equals;

import com.frogparser.flow.domain.command.function.family.compare.AbstractCompareFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class NotGreaterOrEqualsFunctionCommand extends AbstractCompareFunctionCommand {

    private GreaterOrEqualsVariableType variableType;
    private FlowVariable parameterOne;
    private FlowVariable parameterTwo;

    @NotNull
    public GreaterOrEqualsVariableType getVariableType() {
        return variableType;
    }

    public NotGreaterOrEqualsFunctionCommand setVariableType(GreaterOrEqualsVariableType variableType) {
        this.variableType = variableType;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterOne() {
        return parameterOne;
    }

    public NotGreaterOrEqualsFunctionCommand setParameterOne(FlowVariable parameterOne) {
        this.parameterOne = parameterOne;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterTwo() {
        return parameterTwo;
    }

    public NotGreaterOrEqualsFunctionCommand setParameterTwo(FlowVariable parameterTwo) {
        this.parameterTwo = parameterTwo;
        return this;
    }
}
