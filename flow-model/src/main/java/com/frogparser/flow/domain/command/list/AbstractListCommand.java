package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class AbstractListCommand extends AbstractCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public AbstractListCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
