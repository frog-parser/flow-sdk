package com.frogparser.flow.domain.command.function.family.string;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class StrMatchesFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable parameter;
    private FlowVariable regularExpression;
    private FlowVariable result;

    @Valid
    @NotNull
    public FlowVariable getParameter() {
        return parameter;
    }

    public StrMatchesFunctionCommand setParameter(FlowVariable parameter) {
        this.parameter = parameter;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getRegularExpression() {
        return regularExpression;
    }

    public StrMatchesFunctionCommand setRegularExpression(FlowVariable regularExpression) {
        this.regularExpression = regularExpression;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public StrMatchesFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
