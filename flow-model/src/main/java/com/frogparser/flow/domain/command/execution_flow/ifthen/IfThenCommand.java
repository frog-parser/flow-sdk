package com.frogparser.flow.domain.command.execution_flow.ifthen;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class IfThenCommand extends AbstractCommand {

    private FlowVariable ifVariable;
    private List<AbstractCommand> thenCommands;

    @Valid
    @NotNull
    public FlowVariable getIfVariable() {
        return ifVariable;
    }

    public IfThenCommand setIfVariable(FlowVariable ifVariable) {
        this.ifVariable = ifVariable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getThenCommands() {
        return thenCommands;
    }

    public IfThenCommand setThenCommands(List<AbstractCommand> thenCommands) {
        this.thenCommands = thenCommands;
        return this;
    }
}
