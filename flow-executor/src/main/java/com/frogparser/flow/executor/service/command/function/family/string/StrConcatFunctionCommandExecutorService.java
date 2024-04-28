package com.frogparser.flow.executor.service.command.function.family.string;

import com.frogparser.flow.domain.command.function.family.string.StrConcatFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StrConcatFunctionCommandExecutorService extends AbstractCommandExecutorService<StrConcatFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, StrConcatFunctionCommand command) throws CommandExecutionException {

        String result = "";

        if (Objects.nonNull(command.getParameters())) {

            for (final FlowVariable flowVariableString : command.getParameters()) {

                result = result.concat(getRunTimeVariableValue(flowContext, flowVariableString, StringRunTimeVariable.class));

            }

            flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new StringRunTimeVariable(result));

        } else {
            throw new CommandExecutionException("Parameters variable is not set in command");
        }

    }
}
