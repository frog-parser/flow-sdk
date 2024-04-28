package com.frogparser.flow.domain.command.function.family.compare;

import com.frogparser.flow.domain.command.function.AbstractFunctionCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public abstract class AbstractCompareFunctionCommand extends AbstractFunctionCommand {

    private FlowVariable result;

    @Valid
    @NotNull
    public FlowVariable getResult() {
        return result;
    }

    public AbstractCompareFunctionCommand setResult(FlowVariable result) {
        this.result = result;
        return this;
    }
}
