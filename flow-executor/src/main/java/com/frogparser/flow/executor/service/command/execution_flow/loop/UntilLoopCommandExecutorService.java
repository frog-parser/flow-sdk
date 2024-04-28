package com.frogparser.flow.executor.service.command.execution_flow.loop;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.UntilLoopCommand;
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
public class UntilLoopCommandExecutorService extends AbstractCommandExecutorService<UntilLoopCommand> {

    private final ApplicationContext applicationContext;

    public UntilLoopCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, UntilLoopCommand command) throws CommandExecutionException {

        CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        boolean breakLoopFlag;

        final FlowVariable variable = command.getVariable();

        if (Objects.nonNull(variable)) {

            final String variableName = variable.getName();

            if (Objects.nonNull(variableName) && !variableName.isBlank()) {

                final List<AbstractCommand> commands = command.getCommands();

                if (Objects.nonNull(commands)) {

                    do {

                        for (final AbstractCommand cmd : commands) {

                            try {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                            } finally {
                                flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                            }

                        }

                        final AbstractRunTimeVariable<?> rv = flowContext.getVariables().get(variableName);

                        if (Objects.nonNull(rv)) {

                            if (rv instanceof BooleanRunTimeVariable booleanRunTimeVariable) {

                                final Boolean flag = booleanRunTimeVariable.getValue();

                                if (Objects.nonNull(flag)) {

                                    breakLoopFlag = flag;

                                } else {

                                    throw new CommandExecutionException("Variable value is not set");

                                }

                            } else {
                                throw new CommandExecutionException("Expected variable type is Boolean but actual: '%s'".formatted(rv.getClass().getSimpleName()));
                            }

                        } else {
                            throw new CommandExecutionException("Variable not found");
                        }

                    } while (breakLoopFlag);

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
}
