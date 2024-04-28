package com.frogparser.flow.executor.service.command.function.family.logical;

import com.frogparser.flow.domain.command.function.family.logical.NegateFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class NegateFunctionCommandExecutorService extends AbstractCommandExecutorService<NegateFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, NegateFunctionCommand command) throws CommandExecutionException {
        flowContext.getVariables()
                .put(getNotBlankVariableName(command.getParameter()),
                        new BooleanRunTimeVariable(
                                !getContextVariableValue(flowContext, command.getParameter(), Boolean.class)
                        )
                );
    }
}
