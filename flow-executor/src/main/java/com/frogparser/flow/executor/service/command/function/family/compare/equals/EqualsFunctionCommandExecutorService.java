package com.frogparser.flow.executor.service.command.function.family.compare.equals;

import com.frogparser.flow.domain.command.function.family.compare.equals.EqualsFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EqualsFunctionCommandExecutorService extends AbstractCommandExecutorService<EqualsFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, EqualsFunctionCommand command) throws CommandExecutionException {
        getContextVariables(flowContext).put(
                getNotBlankVariableName(command.getResult()),
                new BooleanRunTimeVariable(
                        Objects.equals(
                                getOptionalRunTimeVariableValue(flowContext, command.getParameterOne()),
                                getOptionalRunTimeVariableValue(flowContext, command.getParameterTwo())
                        )
                ));
    }
}
