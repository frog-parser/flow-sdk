package com.frogparser.flow.domain.command.execution_flow.loop;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UntilLoopCommand extends AbstractLoopCommand {

    private FlowVariable variable;
    private List<AbstractCommand> commands;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public UntilLoopCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getCommands() {
        return commands;
    }

    public UntilLoopCommand setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
        return this;
    }
}
