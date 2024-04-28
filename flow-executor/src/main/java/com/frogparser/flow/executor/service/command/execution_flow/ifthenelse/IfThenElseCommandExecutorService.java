package com.frogparser.flow.executor.service.command.execution_flow.ifthenelse;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.execution_flow.ifthenelse.IfThenElseCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import com.frogparser.flow.executor.service.command.CommandExecutorService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IfThenElseCommandExecutorService extends AbstractCommandExecutorService<IfThenElseCommand> {

    private final ApplicationContext applicationContext;

    public IfThenElseCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, IfThenElseCommand command) throws CommandExecutionException {

        final CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        final FlowVariable ifVariable = command.getIfVariable();

        if (Objects.nonNull(ifVariable)) {

            final String ifVariableName = ifVariable.getName();

            if (Objects.nonNull(ifVariableName) && !ifVariableName.isBlank()) {

                if (Boolean.TRUE.equals(flowContext.getVariables().get(ifVariableName).getValue())) {

                    final List<AbstractCommand> thenCommands = command.getThenCommands();

                    if (Objects.nonNull(thenCommands)) {

                        for (final AbstractCommand cmd : thenCommands) {

                            try {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                            } finally {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                            }

                        }

                    } else {

                        throw new CommandExecutionException("Then commands is not set");

                    }

                } else {

                    final List<AbstractCommand> elseCommands = command.getElseCommands();

                    if (Objects.nonNull(elseCommands)) {

                        for (final AbstractCommand cmd : elseCommands) {

                            try {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                            } finally {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                            }

                        }

                    } else {

                        throw new CommandExecutionException("Else commands is not set");

                    }

                }

            } else {
                throw new CommandExecutionException("IfVariable name can not be blank");
            }

        } else {
            throw new CommandExecutionException("IfVariable is not set");
        }

    }
}
