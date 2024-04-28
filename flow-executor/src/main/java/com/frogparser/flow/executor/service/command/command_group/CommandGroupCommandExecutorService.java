package com.frogparser.flow.executor.service.command.command_group;

import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.command_group.CommandGroupCommand;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import com.frogparser.flow.executor.service.command.CommandExecutorService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CommandGroupCommandExecutorService extends AbstractCommandExecutorService<CommandGroupCommand> {

    private final ApplicationContext applicationContext;

    public CommandGroupCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, CommandGroupCommand command) throws CommandExecutionException {

        final CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        for (final AbstractCommand cmd : command.getCommands()) {

            try {
                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
            } finally {
                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
            }

        }

    }
}
