package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.command.list.FilterListCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import com.frogparser.flow.executor.service.command.CommandExecutorService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterListCommandExecutorService extends AbstractCommandExecutorService<FilterListCommand> {

    private final ApplicationContext applicationContext;

    public FilterListCommandExecutorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, FilterListCommand command) throws CommandExecutionException {

        final CommandExecutorService commandExecutorService = applicationContext.getBean(CommandExecutorService.class);

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);

        final List<Object> resultList = new ArrayList<>();

        for (final Object item : list) {

            flowContext.getVariables().put(
                    getNotBlankVariableName(command.getCurrentItemVariable()),
                    createRunTimeVariable(item)
            );

            for (final AbstractCommand cmd : command.getFilterCommands()) {

                try {
                    flowContext.setRecursionDepth(flowContext.getRecursionDepth() + 1);
                    commandExecutorService.execute(flowExecutionSettings, flowContext, cmd);
                } finally {
                    flowContext.setRecursionDepth(flowContext.getRecursionDepth() - 1);
                }

            }

            final Boolean flag = getContextVariableValue(flowContext, command.getFilterFlagVariable(), Boolean.class);

            if (flag) {
                resultList.add(item);
            }

        }

        flowContext.getVariables().put(
                getNotBlankVariableName(command.getNewListVariable()),
                createRunTimeVariable(resultList)
        );

    }
}
