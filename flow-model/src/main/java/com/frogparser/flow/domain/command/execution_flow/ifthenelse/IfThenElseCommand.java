package com.frogparser.flow.domain.command.execution_flow.ifthenelse;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class IfThenElseCommand extends AbstractCommand {

    private FlowVariable ifVariable;
    private List<AbstractCommand> thenCommands;
    private List<AbstractCommand> elseCommands;

    @Valid
    @NotNull
    public FlowVariable getIfVariable() {
        return ifVariable;
    }

    public IfThenElseCommand setIfVariable(FlowVariable ifVariable) {
        this.ifVariable = ifVariable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getThenCommands() {
        return thenCommands;
    }

    public IfThenElseCommand setThenCommands(List<AbstractCommand> thenCommands) {
        this.thenCommands = thenCommands;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getElseCommands() {
        return elseCommands;
    }

    public IfThenElseCommand setElseCommands(List<AbstractCommand> elseCommands) {
        this.elseCommands = elseCommands;
        return this;
    }
}
