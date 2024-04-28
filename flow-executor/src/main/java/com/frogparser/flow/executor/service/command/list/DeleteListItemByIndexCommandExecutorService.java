package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.DeleteListItemByIndexCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteListItemByIndexCommandExecutorService extends AbstractCommandExecutorService<DeleteListItemByIndexCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, DeleteListItemByIndexCommand command) throws CommandExecutionException {
        getContextVariableValue(flowContext, command.getVariable(), List.class).remove(
                getContextVariableValue(flowContext, command.getIndexVariable(), Integer.class)
        );
    }
}
