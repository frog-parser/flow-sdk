package com.frogparser.flow.executor.service.command.execution_flow.loop;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.WhileLoopCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
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
public class WhileLoopCommandExecutorService extends AbstractCommandExecutorService<WhileLoopCommand> {

    private final ApplicationContext applicationContext;

    public WhileLoopCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, WhileLoopCommand command) throws CommandExecutionException {

        CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        final FlowVariable variable = command.getVariable();

        if (Objects.nonNull(variable)) {

            final String variableName = variable.getName();

            if (Objects.nonNull(variableName) && !variableName.isBlank()) {

                final List<AbstractCommand> commands = command.getCommands();

                if (Objects.nonNull(commands)) {

                    boolean breakLoopFlag = getFlag(flowContext, variableName);

                    while (breakLoopFlag) {

                        for (final AbstractCommand cmd : commands) {

                            try {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                            } finally {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                            }

                        }

                        breakLoopFlag = getFlag(flowContext, variableName);

                    }

                } else {
                    throw new CommandExecutionException("Commands is not set");
                }

            } else {
                throw new CommandExecutionException("Variable name is not set in command");
            }

        } else {
            throw new CommandExecutionException("Variable is not set in command");
        }

    }

    private boolean getFlag(FlowContext flowContext, String variableName) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> rv = flowContext.getVariables().get(variableName);

        if (Objects.nonNull(rv)) {

            if (rv instanceof BooleanRunTimeVariable booleanRunTimeVariable) {

                final Boolean flag = booleanRunTimeVariable.getValue();

                if (Objects.nonNull(flag)) {

                    return flag;

                } else {

                    throw new CommandExecutionException("Variable value is not set");

                }

            } else {
                throw new CommandExecutionException("Expected variable type is Boolean but actual: '%s'".formatted(rv.getClass().getSimpleName()));
            }

        } else {
            throw new CommandExecutionException("Variable not found");
        }

    }

}
