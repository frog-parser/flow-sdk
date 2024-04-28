package com.frogparser.flow.domain.command.function.family.compare.less;

import com.frogparser.flow.domain.command.function.family.compare.AbstractCompareFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class LessFunctionCommand extends AbstractCompareFunctionCommand {

    private LessVariableType variableType;
    private FlowVariable parameterOne;
    private FlowVariable parameterTwo;

    @NotNull
    public LessVariableType getVariableType() {
        return variableType;
    }

    public LessFunctionCommand setVariableType(LessVariableType variableType) {
        this.variableType = variableType;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterOne() {
        return parameterOne;
    }

    public LessFunctionCommand setParameterOne(FlowVariable parameterOne) {
        this.parameterOne = parameterOne;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParameterTwo() {
        return parameterTwo;
    }

    public LessFunctionCommand setParameterTwo(FlowVariable parameterTwo) {
        this.parameterTwo = parameterTwo;
        return this;
    }
}
