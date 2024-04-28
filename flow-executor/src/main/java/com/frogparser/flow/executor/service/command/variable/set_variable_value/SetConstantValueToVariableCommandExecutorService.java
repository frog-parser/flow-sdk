package com.frogparser.flow.executor.service.command.variable.set_variable_value;

import com.frogparser.flow.domain.command.variable.set_variable_value.SetConstantValueToVariableCommand;
import com.frogparser.flow.domain.command.variable.set_variable_value.SetConstantValueToVariableTypeEnum;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.runtime_variable.list.*;
import com.frogparser.flow.domain.variable_value.*;
import com.frogparser.flow.domain.variable_value.list.*;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

import static com.frogparser.flow.domain.command.variable.set_variable_value.SetConstantValueToVariableTypeEnum.*;

@Service
public class SetConstantValueToVariableCommandExecutorService extends AbstractCommandExecutorService<SetConstantValueToVariableCommand> {

    private final Map<SetConstantValueToVariableTypeEnum, Class<? extends AbstractRunTimeVariable<?>>> fwToRTVmap = Map.ofEntries(
            Map.entry(BIG_DECIMAL, BigDecimalRunTimeVariable.class),
            Map.entry(BIG_DECIMAL_LIST, BigDecimalListRunTimeVariable.class),
            Map.entry(BOOLEAN, BooleanRunTimeVariable.class),
            Map.entry(BOOLEAN_LIST, BooleanListRunTimeVariable.class),
            Map.entry(DIMENSION, DimensionRunTimeVariable.class),
            Map.entry(INTEGER, IntegerRunTimeVariable.class),
            Map.entry(INTEGER_LIST, IntegerListRunTimeVariable.class),
            Map.entry(LOCAL_DATE, LocalDateRunTimeVariable.class),
            Map.entry(LOCAL_DATE_LIST, LocalDateListRunTimeVariable.class),
            Map.entry(LOCAL_DATE_TIME, LocalDateTimeRunTimeVariable.class),
            Map.entry(LOCAL_DATE_TIME_LIST, LocalDateTimeListRunTimeVariable.class),
            Map.entry(LONG, LongRunTimeVariable.class),
            Map.entry(LONG_LIST, LongListRunTimeVariable.class),
            Map.entry(STRING, StringRunTimeVariable.class),
            Map.entry(STRING_LIST, StringListRunTimeVariable.class),
            Map.entry(URL, UrlRunTimeVariable.class),
            Map.entry(URL_LIST, UrlListRunTimeVariable.class)
    );

    private final Map<SetConstantValueToVariableTypeEnum, Class<? extends AbstractVariableValue<?>>> fwToVVmap = Map.ofEntries(
            Map.entry(BIG_DECIMAL, VariableValueBigDecimal.class),
            Map.entry(BIG_DECIMAL_LIST, VariableValueBigDecimalList.class),
            Map.entry(BOOLEAN, VariableValueBoolean.class),
            Map.entry(BOOLEAN_LIST, VariableValueBooleanList.class),
            Map.entry(DIMENSION, VariableValueDimension.class),
            Map.entry(INTEGER, VariableValueInteger.class),
            Map.entry(INTEGER_LIST, VariableValueIntegerList.class),
            Map.entry(LOCAL_DATE, VariableValueLocalDate.class),
            Map.entry(LOCAL_DATE_LIST, VariableValueLocalDateList.class),
            Map.entry(LOCAL_DATE_TIME, VariableValueLocalDateTime.class),
            Map.entry(LOCAL_DATE_TIME_LIST, VariableValueLocalDateTimeList.class),
            Map.entry(LONG, VariableValueLong.class),
            Map.entry(LONG_LIST, VariableValueLongList.class),
            Map.entry(STRING, VariableValueString.class),
            Map.entry(STRING_LIST, VariableValueStringList.class),
            Map.entry(URL, VariableValueUrl.class),
            Map.entry(URL_LIST, VariableValueUrlList.class)
    );

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SetConstantValueToVariableCommand command) throws CommandExecutionException {

        final SetConstantValueToVariableTypeEnum variableType = command.getVariableType();
        final FlowVariable flowVariable = command.getVariable();
        final AbstractVariableValue<?> variableValue = command.getValue();

        if (Objects.nonNull(flowVariable)) {

            final Class<? extends AbstractRunTimeVariable<?>> runTimeVariableClazz = fwToRTVmap.get(variableType);

            if (Objects.nonNull(runTimeVariableClazz)) {

                try {
                    try {

                        final Object value = variableValue.getValue();

                        if (Objects.nonNull(value)) {

                            final Class<? extends AbstractVariableValue<?>> variableValueClazz = fwToVVmap.get(variableType);

                            if (Objects.isNull(variableValueClazz)) {
                                throw new CommandExecutionException("Expected variable type: '%s' is not defined".formatted(flowVariable.getClass()));
                            }

                            if (Objects.equals(variableValue.getClass(), variableValueClazz)) {

                                final String flowVariableName = flowVariable.getName();

                                if (Objects.nonNull(flowVariableName) && !flowVariableName.isBlank()) {

                                    final Class<?> argumentClass = GenericTypeResolver.resolveTypeArgument(runTimeVariableClazz, AbstractRunTimeVariable.class);

                                    final AbstractRunTimeVariable<?> runTimeVariable = runTimeVariableClazz.getDeclaredConstructor(argumentClass).newInstance(value);

                                    flowContext.getVariables().put(flowVariableName, runTimeVariable);
                                } else {
                                    throw new CommandExecutionException("Variable name can not be empty");
                                }

                            } else {
                                throw new CommandExecutionException("Expected variable type: '%s' but found: '%s'".formatted(variableValueClazz.getSimpleName(), variableValue.getClass().getSimpleName()));
                            }

                        } else {
                            throw new CommandExecutionException("Variable value can not be empty");
                        }

                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new CommandExecutionException("Can not create runtime variable: '%s'".formatted(e.getMessage()), e);
                    }
                } catch (NoSuchMethodException e) {
                    throw new CommandExecutionException("Can not create runtime variable: '%s'".formatted(e.getMessage()), e);
                }

            } else {
                throw new CommandExecutionException("Variable has incorrect type: '%s'".formatted(flowVariable.getClass().getSimpleName()));
            }

        } else {
            throw new CommandExecutionException("Variable can not be empty");
        }

    }
}
