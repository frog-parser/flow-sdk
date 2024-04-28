package com.frogparser.flow.executor.service.command.function.family.string;

import com.frogparser.flow.domain.command.function.family.string.StrIsBlankFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class StrIsBlankFunctionCommandExecutorService extends AbstractCommandExecutorService<StrIsBlankFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, StrIsBlankFunctionCommand command) throws CommandExecutionException {
        flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new BooleanRunTimeVariable(
                        getRunTimeVariableValue(flowContext, command.getParameter(), StringRunTimeVariable.class).isBlank()
                )
        );
    }
}
