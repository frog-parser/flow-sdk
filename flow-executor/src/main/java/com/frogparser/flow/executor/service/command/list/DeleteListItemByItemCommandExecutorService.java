package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.DeleteListItemByItemCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteListItemByItemCommandExecutorService extends AbstractCommandExecutorService<DeleteListItemByItemCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, DeleteListItemByItemCommand command) throws CommandExecutionException {
        getContextVariableValue(flowContext, command.getVariable(), List.class).remove(
                getRunTimeVariable(flowContext, command.getItemVariable()).getValue()
        );
    }
}
