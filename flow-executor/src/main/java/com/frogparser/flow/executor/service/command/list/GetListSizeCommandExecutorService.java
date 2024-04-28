package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.GetListSizeCommand;
import com.frogparser.flow.domain.runtime_variable.IntegerRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListSizeCommandExecutorService extends AbstractCommandExecutorService<GetListSizeCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GetListSizeCommand command) throws CommandExecutionException {

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);

        flowContext.getVariables().put(
                getNotBlankVariableName(command.getVariable()),
                new IntegerRunTimeVariable(list.size())
        );

    }
}
