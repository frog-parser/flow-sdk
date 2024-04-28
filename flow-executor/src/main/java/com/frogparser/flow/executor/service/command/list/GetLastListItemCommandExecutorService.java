package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.GetLastListItemCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetLastListItemCommandExecutorService extends AbstractCommandExecutorService<GetLastListItemCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GetLastListItemCommand command) throws CommandExecutionException {

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);

        if (!list.isEmpty()) {

            flowContext.getVariables().put(
                    getNotBlankVariableName(command.getItemVariable()),
                    createRunTimeVariable(list.get(list.size() - 1))
            );

        } else {
            throw new CommandExecutionException("List is empty");
        }

    }
}
