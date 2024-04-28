package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FilterListCommand extends AbstractListCommand {

    private FlowVariable currentItemVariable;
    private List<AbstractCommand> filterCommands;
    private FlowVariable filterFlagVariable;
    private FlowVariable newListVariable;

    @Valid
    @NotNull
    public FlowVariable getCurrentItemVariable() {
        return currentItemVariable;
    }

    public FilterListCommand setCurrentItemVariable(FlowVariable currentItemVariable) {
        this.currentItemVariable = currentItemVariable;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getFilterCommands() {
        return filterCommands;
    }

    public FilterListCommand setFilterCommands(List<AbstractCommand> filterCommands) {
        this.filterCommands = filterCommands;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getFilterFlagVariable() {
        return filterFlagVariable;
    }

    public FilterListCommand setFilterFlagVariable(FlowVariable filterFlagVariable) {
        this.filterFlagVariable = filterFlagVariable;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getNewListVariable() {
        return newListVariable;
    }

    public FilterListCommand setNewListVariable(FlowVariable newListVariable) {
        this.newListVariable = newListVariable;
        return this;
    }
}
