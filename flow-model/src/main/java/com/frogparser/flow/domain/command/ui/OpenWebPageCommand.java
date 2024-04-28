package com.frogparser.flow.domain.command.ui;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class OpenWebPageCommand extends AbstractCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public OpenWebPageCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
