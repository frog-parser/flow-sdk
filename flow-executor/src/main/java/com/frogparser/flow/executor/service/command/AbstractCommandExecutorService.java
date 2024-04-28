package com.frogparser.flow.executor.service.command;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.runtime_variable.*;
import com.frogparser.flow.domain.runtime_variable.list.*;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class AbstractCommandExecutorService<T extends AbstractCommand> {

    private Optional<Class<?>> findRunTimeClass(Object o) {
        return switch (o) {
            case BigDecimal _ -> Optional.of(BigDecimalRunTimeVariable.class);
            case Boolean _ -> Optional.of(BooleanRunTimeVariable.class);
            case Dimension _ -> Optional.of(DimensionRunTimeVariable.class);
            case Integer _ -> Optional.of(IntegerRunTimeVariable.class);
            case LocalDate _ -> Optional.of(LocalDateRunTimeVariable.class);
            case LocalDateTime _ -> Optional.of(LocalDateTimeRunTimeVariable.class);
            case Location _ -> Optional.of(LocationRunTimeVariable.class);
            case Long _ -> Optional.of(LongRunTimeVariable.class);
            case Rectangle _ -> Optional.of(RectangleRunTimeVariable.class);
            case String _ -> Optional.of(StringRunTimeVariable.class);
            case URL _ -> Optional.of(UrlRunTimeVariable.class);
            case WebElement _ -> Optional.of(WebElementRunTimeVariable.class);
            case SearchContext _ -> Optional.of(SearchContextRunTimeVariable.class);
            case null, default -> Optional.empty();
        };
    }

    private Optional<Class<?>> findRunTimeListClass(Object o) {
        return switch (o) {
            case BigDecimal _ -> Optional.of(BigDecimalListRunTimeVariable.class);
            case Boolean _ -> Optional.of(BooleanListRunTimeVariable.class);
            case Dimension _ -> Optional.of(DimensionListRunTimeVariable.class);
            case Integer _ -> Optional.of(IntegerListRunTimeVariable.class);
            case LocalDate _ -> Optional.of(LocalDateListRunTimeVariable.class);
            case LocalDateTime _ -> Optional.of(LocalDateTimeListRunTimeVariable.class);
            case Location _ -> Optional.of(LocationListRunTimeVariable.class);
            case Long _ -> Optional.of(LongListRunTimeVariable.class);
            case Rectangle _ -> Optional.of(RectangleListRunTimeVariable.class);
            case String _ -> Optional.of(StringListRunTimeVariable.class);
            case URL _ -> Optional.of(UrlListRunTimeVariable.class);
            case WebElement _ -> Optional.of(WebElementListRunTimeVariable.class);
            case SearchContext _ -> Optional.of(SearchContextListRunTimeVariable.class);
            case null, default -> Optional.empty();
        };
    }

    protected final Class<T> clazz = calculateClazz();

    @SuppressWarnings("unchecked")
    private Class<T> calculateClazz() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractCommandExecutorService.class);
    }

    public void executeAbstract(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, AbstractCommand command) throws CommandExecutionException {
        this.execute(flowExecutionSettings, flowContext, clazz.cast(command));
    }

    public abstract void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, T command) throws CommandExecutionException;

    protected WebDriver getWebDriver(FlowContext flowContext) {
        return validateWebDriver(flowContext);
    }

    protected Map<String, AbstractRunTimeVariable<?>> getContextVariables(FlowContext flowContext) {
        Objects.requireNonNull(flowContext);
        final var variables = flowContext.getVariables();
        Objects.requireNonNull(variables);
        return variables;
    }

    protected void validateContextVariableName(String name) throws CommandExecutionException {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new CommandExecutionException("Variable name should be provided.");
        }
    }

    protected WebDriver validateWebDriver(FlowContext flowContext) {
        Objects.requireNonNull(flowContext);
        final WebDriver webDriver;
        webDriver = flowContext.getWebDriver();
        Objects.requireNonNull(webDriver);
        return webDriver;
    }

    protected Optional<String> getOptionalVariableName(Supplier<FlowVariable> flowVariableSupplier) {
        Objects.requireNonNull(flowVariableSupplier);
        return Optional.of(flowVariableSupplier).map(Supplier::get).map(FlowVariable::getName);
    }

    protected String getVariableName(Supplier<FlowVariable> flowVariableSupplier) throws CommandExecutionException {
        return getOptionalVariableName(flowVariableSupplier)
                .orElseThrow(() -> new CommandExecutionException("Variable name can not be empty."));
    }

    protected String getNotBlankVariableName(Supplier<FlowVariable> flowVariableSupplier) throws CommandExecutionException {
        return getOptionalVariableName(flowVariableSupplier)
                .filter(Predicate.not(String::isBlank))
                .orElseThrow(() -> new CommandExecutionException("Variable name can not be empty."));
    }

    protected String getNotBlankVariableName(FlowVariable flowVariable) throws CommandExecutionException {
        return getNotBlankVariableName(() -> flowVariable);
    }

    protected AbstractRunTimeVariable<?> createRunTimeVariable(Object value) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> result;

        if (Objects.nonNull(value)) {

            final Class<?> valueClass = value.getClass();

            final Optional<Class<?>> scalarValueClassOptional = findRunTimeClass(value);

            if (scalarValueClassOptional.isPresent()) {

                try {

                    try {

                        final Class<?> argumentClass = GenericTypeResolver.resolveTypeArgument(scalarValueClassOptional.get(), AbstractRunTimeVariable.class);

                        result = (AbstractRunTimeVariable<?>) scalarValueClassOptional.get().getDeclaredConstructor(argumentClass).newInstance(value);

                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new CommandExecutionException("Can not create variable instance: '%s'".formatted(e.getMessage()), e);
                    }

                } catch (NoSuchMethodException e) {
                    throw new CommandExecutionException("Can not create variable instance: '%s'".formatted(e.getMessage()), e);
                }

            } else {

                if (value instanceof AbstractListRunTimeVariable<?>) {

                    final Class<?> genericParameterClass = GenericTypeResolver.resolveTypeArgument(valueClass, AbstractListRunTimeVariable.class);

                    if (Objects.nonNull(genericParameterClass)) {

                        final Optional<Class<?>> rtlvClassOptional = findRunTimeListClass(value);

                        if (rtlvClassOptional.isPresent()) {

                            try {

                                try {

                                    final Class<?> argumentClass = GenericTypeResolver.resolveTypeArgument(rtlvClassOptional.get(), AbstractRunTimeVariable.class);

                                    result = (AbstractListRunTimeVariable<?>) rtlvClassOptional.get().getDeclaredConstructor(argumentClass).newInstance(value);

                                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                    throw new CommandExecutionException("Can not create variable instance: '%s'".formatted(e.getMessage()), e);
                                }

                            } catch (NoSuchMethodException e) {
                                throw new CommandExecutionException("Can not create variable instance: '%s'".formatted(e.getMessage()), e);
                            }

                        } else {
                            throw new CommandExecutionException("Invalid value list type: '%s'".formatted(genericParameterClass.getSimpleName()));
                        }

                    } else {
                        throw new CommandExecutionException("Invalid value list type");
                    }

                } else {
                    throw new CommandExecutionException("Invalid value type: '%s'".formatted(valueClass.getSimpleName()));
                }

            }

        } else {
            throw new CommandExecutionException("Value can not be empty");
        }

        return result;

    }

    protected AbstractRunTimeVariable<?> getRunTimeVariable(FlowContext flowContext, FlowVariable flowVariable) throws CommandExecutionException {

        if (Objects.isNull(flowContext)) {
            throw new CommandExecutionException("Flow context can not be empty");
        }

        final String name = getNotBlankVariableName(flowVariable);

        final AbstractRunTimeVariable<?> variable = flowContext.getVariables().get(name);

        if (Objects.isNull(variable)) {
            throw new CommandExecutionException("Variable with name '%s' not found".formatted(name));
        }

        return variable;
    }

    protected AbstractRunTimeVariable<?> getRunTimeVariable(FlowContext flowContext, FlowVariable flowVariable, Class<? extends AbstractRunTimeVariable<?>> requiredClass) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> variable = getRunTimeVariable(flowContext, flowVariable);

        if (requiredClass.isAssignableFrom(variable.getClass())) {
            return requiredClass.cast(variable);
        } else {
            throw new CommandExecutionException("Variable with name '%s' has actual type '%s' but not '%s'".
                    formatted(flowVariable.getName(), variable.getClass().getSimpleName(), requiredClass.getSimpleName()));
        }

    }

    protected <V> V getRunTimeVariableValue(FlowContext flowContext, FlowVariable flowVariable, Class<? extends AbstractRunTimeVariable<V>> requiredClass) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> variable = getRunTimeVariable(flowContext, flowVariable);

        if (requiredClass.isAssignableFrom(variable.getClass())) {
            return requiredClass.cast(variable).getValue();
        } else {
            throw new CommandExecutionException("Variable with name '%s' has actual type '%s' but not '%s'".
                    formatted(flowVariable.getName(), variable.getClass().getSimpleName(), requiredClass.getSimpleName()));
        }

    }

    protected Optional<Object> getOptionalRunTimeVariableValue(FlowContext flowContext, FlowVariable flowVariable) throws CommandExecutionException {
        return Optional.ofNullable(getRunTimeVariable(flowContext, flowVariable).getValue());
    }

    protected <N> N getContextVariableValue(FlowContext flowContext, FlowVariable flowVariable, Class<N> requiredClass) throws CommandExecutionException {

        final N result;

        Object variableValue = getRunTimeVariable(flowContext, flowVariable).getValue();

        if (Objects.nonNull(variableValue)) {

            if (requiredClass.isAssignableFrom(variableValue.getClass())) {
                result = requiredClass.cast(variableValue);
            } else {
                throw new CommandExecutionException("Variable value with name '%s' has actual type '%s' but not '%s'".
                        formatted(flowVariable.getName(), variableValue.getClass().getSimpleName(), requiredClass.getSimpleName()));
            }

        } else {
            throw new CommandExecutionException("Variable with name '%s' can not be empty.".formatted(flowVariable.getName()));
        }

        return result;
    }

}
