package com.frogparser.flow.executor.service.command.list;

import com.frogparser.flow.domain.command.list.ClearListCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClearListCommandExecutorService extends AbstractCommandExecutorService<ClearListCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, ClearListCommand command) throws CommandExecutionException {
        getContextVariableValue(flowContext, command.getVariable(), List.class).clear();
    }
}
