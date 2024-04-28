package com.frogparser.flow.domain.command.variable.save_variable;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SaveVariableCommand extends AbstractCommand {
    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public SaveVariableCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
