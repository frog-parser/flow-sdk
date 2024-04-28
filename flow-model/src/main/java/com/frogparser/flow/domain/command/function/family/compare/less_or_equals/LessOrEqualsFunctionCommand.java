package com.frogparser.flow.domain.command.function.family.compare.less_or_equals;

import com.frogparser.flow.domain.command.function.family.compare.AbstractCompareFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class LessOrEqualsFunctionCommand extends AbstractCompareFunctionCommand {

    private LessOrEqualsVariableType variableType;
    private FlowVariable parameterOne;
    private FlowVariable parameterTwo;

    @NotNull
    public LessOrEqualsVariableType getVariableType() {
        return variableType;
    }

    public LessOrEqualsFunctionCommand setVariableType(LessOrEqualsVariableType variableType) {
        this.variableType = variableType;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterOne() {
        return parameterOne;
    }

    public LessOrEqualsFunctionCommand setParameterOne(FlowVariable parameterOne) {
        this.parameterOne = parameterOne;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterTwo() {
        return parameterTwo;
    }

    public LessOrEqualsFunctionCommand setParameterTwo(FlowVariable parameterTwo) {
        this.parameterTwo = parameterTwo;
        return this;
    }
}
