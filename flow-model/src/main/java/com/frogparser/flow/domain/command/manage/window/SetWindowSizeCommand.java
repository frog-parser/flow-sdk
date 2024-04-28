package com.frogparser.flow.domain.command.manage.window;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SetWindowSizeCommand extends AbstractCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public SetWindowSizeCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
