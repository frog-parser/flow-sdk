package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class IsListEmptyCommand extends AbstractListCommand {

    private FlowVariable resultVariable;

    @Valid
    @NotNull
    public FlowVariable getResultVariable() {
        return resultVariable;
    }

    public IsListEmptyCommand setResultVariable(FlowVariable resultVariable) {
        this.resultVariable = resultVariable;
        return this;
    }
}
