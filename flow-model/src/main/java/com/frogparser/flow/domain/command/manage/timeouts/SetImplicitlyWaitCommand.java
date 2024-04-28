package com.frogparser.flow.domain.command.manage.timeouts;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SetImplicitlyWaitCommand extends AbstractCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public SetImplicitlyWaitCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
