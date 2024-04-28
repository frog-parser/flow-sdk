package com.frogparser.flow.executor.service.command.function.family.string;

import com.frogparser.flow.domain.command.function.family.string.StrTrimFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class StrTrimFunctionCommandExecutorService extends AbstractCommandExecutorService<StrTrimFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, StrTrimFunctionCommand command) throws CommandExecutionException {
        flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new StringRunTimeVariable(
                        getRunTimeVariableValue(flowContext, command.getParameter(), StringRunTimeVariable.class).trim()
                )
        );
    }
}
