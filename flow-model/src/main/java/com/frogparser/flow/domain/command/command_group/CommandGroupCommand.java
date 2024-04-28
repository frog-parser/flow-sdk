package com.frogparser.flow.domain.command.command_group;

import com.frogparser.flow.domain.command.AbstractCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CommandGroupCommand extends AbstractCommand {

    private List<AbstractCommand> commands;

    @Valid
    @NotNull
    public List<AbstractCommand> getCommands() {
        return commands;
    }

    public CommandGroupCommand setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
        return this;
    }
}
