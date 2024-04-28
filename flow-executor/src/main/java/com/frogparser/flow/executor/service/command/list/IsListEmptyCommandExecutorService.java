package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.IsListEmptyCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IsListEmptyCommandExecutorService extends AbstractCommandExecutorService<IsListEmptyCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, IsListEmptyCommand command) throws CommandExecutionException {

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);

        flowContext.getVariables().put(
                getNotBlankVariableName(command.getVariable()),
                new BooleanRunTimeVariable(list.isEmpty())
        );

    }
}
