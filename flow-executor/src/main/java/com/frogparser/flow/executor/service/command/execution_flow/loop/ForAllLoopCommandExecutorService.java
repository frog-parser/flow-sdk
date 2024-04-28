package com.frogparser.flow.executor.service.command.execution_flow.loop;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.execution_flow.loop.ForAllLoopCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.list.AbstractListRunTimeVariable;
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
public class ForAllLoopCommandExecutorService extends AbstractCommandExecutorService<ForAllLoopCommand> {

    private final ApplicationContext applicationContext;

    public ForAllLoopCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, ForAllLoopCommand command) throws CommandExecutionException {

        final CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        final FlowVariable variable = command.getVariable();

        final FlowVariable itemVariable = command.getItemVariable();

        final List<AbstractCommand> commands = command.getCommands();

        if (Objects.nonNull(commands)) {

            if (Objects.nonNull(itemVariable)) {

                final String itemVariableName = itemVariable.getName();

                if (Objects.nonNull(itemVariableName) && !itemVariableName.isBlank()) {

                    if (Objects.nonNull(variable)) {

                        final String variableName = variable.getName();

                        if (Objects.nonNull(variableName) && !variableName.isBlank()) {

                            final AbstractRunTimeVariable<?> rtv = flowContext.getVariables().get(variableName);

                            if (Objects.nonNull(rtv)) {

                                if (rtv instanceof AbstractListRunTimeVariable<?> abstractListRunTimeVariable) {

                                    final List<?> itemList = abstractListRunTimeVariable.getValue();

                                    if (Objects.nonNull(itemList)) {

                                        for (final Object item : itemList) {

                                            if (Objects.nonNull(item)) {

                                                flowContext.getVariables().put(itemVariableName, createRunTimeVariable(item));

                                                for (final AbstractCommand cmd : commands) {

                                                    try {
                                                        flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                                                        commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                                                    } finally {
                                                        flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                                                    }

                                                }

                                            } else {
                                                throw new CommandExecutionException("List item can not be empty");
                                            }

                                        }

                                    } else {
                                        throw new CommandExecutionException("List items can not be empty");
                                    }

                                } else {
                                    throw new CommandExecutionException("List variable is not of list type");
                                }

                            } else {
                                throw new CommandExecutionException("List variable has not been found");
                            }

                        } else {
                            throw new CommandExecutionException("List variable name can not be empty");
                        }

                    } else {
                        throw new CommandExecutionException("List variable is not set in command");
                    }

                } else {
                    throw new CommandExecutionException("List item variable name can not be blank in command");
                }

            } else {
                throw new CommandExecutionException("List item variable is not set in command");
            }

        } else {
            throw new CommandExecutionException("Commands is not set in command");
        }

    }

}
