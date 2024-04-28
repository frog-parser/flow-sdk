package com.frogparser.flow.domain.command.arithmetic;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DecrementVariableCommand extends AbstractCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public DecrementVariableCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
