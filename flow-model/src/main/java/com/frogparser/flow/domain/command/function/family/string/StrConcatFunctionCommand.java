package com.frogparser.flow.domain.command.function.family.string;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class StrConcatFunctionCommand extends AbstractFunctionCommand {

    private List<FlowVariable> parameters;
    private FlowVariable result;

    @Valid
    @NotNull
    public List<FlowVariable> getParameters() {
        return parameters;
    }

    public StrConcatFunctionCommand setParameters(List<FlowVariable> parameters) {
        this.parameters = parameters;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public StrConcatFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
