package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.GetListItemByIndexCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListItemByIndexCommandExecutorService extends AbstractCommandExecutorService<GetListItemByIndexCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GetListItemByIndexCommand command) throws CommandExecutionException {

        final List<?> list = getContextVariableValue(flowContext, command.getVariable(), List.class);
        final Integer index = getContextVariableValue(flowContext, command.getIndexVariable(), Integer.class);
        final int size = list.size();

        if (size != 0) {

            if (!(index < 0 || index >= size)) {

                flowContext.getVariables().put(
                        getNotBlankVariableName(command.getItemVariable()),
                        createRunTimeVariable(list.get(index))
                );

            } else {
                throw new CommandExecutionException("Index value %d out of bounds of list of size %d".formatted(index, size));
            }

        } else {
            throw new CommandExecutionException("List is empty");
        }

    }
}
