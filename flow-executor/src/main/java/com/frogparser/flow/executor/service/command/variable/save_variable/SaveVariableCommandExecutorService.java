package com.frogparser.flow.executor.service.command.variable.save_variable;

import com.frogparser.flow.domain.command.variable.save_variable.SaveVariableCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import com.frogparser.flow.executor.service.FlowVariableSaveService;
import org.springframework.stereotype.Service;

@Service
public class SaveVariableCommandExecutorService extends AbstractCommandExecutorService<SaveVariableCommand> {

    private final FlowVariableSaveService flowVariableSaveService;

    public SaveVariableCommandExecutorService(FlowVariableSaveService flowVariableSaveService) {
        this.flowVariableSaveService = flowVariableSaveService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SaveVariableCommand command) throws CommandExecutionException {
        final AbstractRunTimeVariable<?> runTimeVariable = getRunTimeVariable(flowContext, command.getVariable());
        flowVariableSaveService.saveVariable(flowContext.getFlowExecutionId(), command.getVariable().getName(), runTimeVariable);
    }

}
