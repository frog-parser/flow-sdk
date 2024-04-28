package com.frogparser.flow.executor.service.command.function.family.logical;

import com.frogparser.flow.domain.command.function.family.logical.AndFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AndFunctionCommandExecutorService extends AbstractCommandExecutorService<AndFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, AndFunctionCommand command) throws CommandExecutionException {

        boolean result = true;

        List<FlowVariable> items = command.getParameters();

        if (Objects.nonNull(items)) {

            if (!items.isEmpty()) {

                for (FlowVariable item : command.getParameters()) {

                    final boolean value = getRunTimeVariableValue(flowContext, item, BooleanRunTimeVariable.class);

                    if (!value) {
                        result = false;
                        break;
                    }

                }

            } else {
                throw new CommandExecutionException("Parameters variable list is empty in command");
            }

        } else {
            throw new CommandExecutionException("Parameters variable is not set in command");
        }

        flowContext.getVariables().put(getNotBlankVariableName(command.getResult()), new BooleanRunTimeVariable(result));

    }
}
