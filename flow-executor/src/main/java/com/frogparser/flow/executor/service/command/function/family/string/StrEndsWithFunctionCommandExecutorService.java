package com.frogparser.flow.executor.service.command.function.family.string;

import com.frogparser.flow.domain.command.function.family.string.StrEndsWithFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class StrEndsWithFunctionCommandExecutorService extends AbstractCommandExecutorService<StrEndsWithFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, StrEndsWithFunctionCommand command) throws CommandExecutionException {
        flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new BooleanRunTimeVariable(
                getRunTimeVariableValue(flowContext, command.getParameterOne(), StringRunTimeVariable.class)
                        .endsWith(getRunTimeVariableValue(flowContext, command.getParameterTwo(), StringRunTimeVariable.class))
        ));
    }
}
