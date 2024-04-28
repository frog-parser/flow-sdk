package com.frogparser.flow.executor.service.command.function.family.compare.less_or_equals;

import com.frogparser.flow.domain.command.function.family.compare.less_or_equals.NotLessOrEqualsFunctionCommand;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotLessOrEqualsFunctionCommandExecutorService extends AbstractCommandExecutorService<NotLessOrEqualsFunctionCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, NotLessOrEqualsFunctionCommand command) throws CommandExecutionException {
        getContextVariables(flowContext).put(
                getNotBlankVariableName(command.getResult()),
                new BooleanRunTimeVariable(
                        !(switch (command.getVariableType()) {
                            case BIG_DECIMAL -> getRunTimeVariableValue(flowContext, command.getParameterOne(), BigDecimalRunTimeVariable.class)
                                    .compareTo(getRunTimeVariableValue(flowContext, command.getParameterOne(), BigDecimalRunTimeVariable.class)) < 0;
                            case INTEGER -> getRunTimeVariableValue(flowContext, command.getParameterOne(), IntegerRunTimeVariable.class)
                                    .compareTo(getRunTimeVariableValue(flowContext, command.getParameterOne(), IntegerRunTimeVariable.class)) < 0;
                            case LOCAL_DATE -> getRunTimeVariableValue(flowContext, command.getParameterOne(), LocalDateRunTimeVariable.class)
                                    .isBefore(getRunTimeVariableValue(flowContext, command.getParameterOne(), LocalDateRunTimeVariable.class));
                            case LOCAL_DATE_TIME -> getRunTimeVariableValue(flowContext, command.getParameterOne(), LocalDateTimeRunTimeVariable.class)
                                    .isBefore(getRunTimeVariableValue(flowContext, command.getParameterOne(), LocalDateTimeRunTimeVariable.class));
                            case LONG -> getRunTimeVariableValue(flowContext, command.getParameterOne(), LongRunTimeVariable.class)
                                    .compareTo(getRunTimeVariableValue(flowContext, command.getParameterOne(), LongRunTimeVariable.class)) < 0;
                            case STRING -> getRunTimeVariableValue(flowContext, command.getParameterOne(), StringRunTimeVariable.class)
                                    .compareTo(getRunTimeVariableValue(flowContext, command.getParameterOne(), StringRunTimeVariable.class)) < 0;
                            case URL -> getRunTimeVariableValue(flowContext, command.getParameterOne(), UrlRunTimeVariable.class).toString()
                                    .compareTo(getRunTimeVariableValue(flowContext, command.getParameterOne(), UrlRunTimeVariable.class).toString()) < 0;
                        }
                                || Objects.equals(
                                getOptionalRunTimeVariableValue(flowContext, command.getParameterOne()),
                                getOptionalRunTimeVariableValue(flowContext, command.getParameterTwo())
                        ))
                ));
    }
}
