package com.frogparser.flow.domain.command.execution_flow.loop;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ForAllLoopCommand extends AbstractLoopCommand {

    private FlowVariable variable;
    private FlowVariable itemVariable;
    private List<AbstractCommand> commands;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public ForAllLoopCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getItemVariable() {
        return itemVariable;
    }

    public ForAllLoopCommand setItemVariable(FlowVariable itemVariable) {
        this.itemVariable = itemVariable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getCommands() {
        return commands;
    }

    public ForAllLoopCommand setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
        return this;
    }
}
