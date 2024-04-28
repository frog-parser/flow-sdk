package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.GetFirstListItemCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFirstListItemCommandExecutorService extends AbstractCommandExecutorService<GetFirstListItemCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GetFirstListItemCommand command) throws CommandExecutionException {

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);

        if (!list.isEmpty()) {

            flowContext.getVariables().put(
                    getNotBlankVariableName(command.getItemVariable()),
                    createRunTimeVariable(list.get(0))
            );

        } else {
            throw new CommandExecutionException("List is empty");
        }

    }
}
