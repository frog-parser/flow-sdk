package com.frogparser.flow.executor.service.command.function.family.string;

import com.frogparser.flow.domain.command.function.family.string.StrLenFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.IntegerRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class StrLenFunctionCommandExecutorService extends AbstractCommandExecutorService<StrLenFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, StrLenFunctionCommand command) throws CommandExecutionException {
        flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new IntegerRunTimeVariable(
                        getRunTimeVariableValue(flowContext, command.getParameter(), StringRunTimeVariable.class).length()
                )
        );
    }
}
