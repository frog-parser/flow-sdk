package com.frogparser.flow.executor.service.command.arithmetic;

import com.frogparser.flow.domain.command.arithmetic.IncrementVariableCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.BigDecimalRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.IntegerRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.LongRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IncrementVariableCommandExecutorService extends AbstractCommandExecutorService<IncrementVariableCommand> {

    private static final Logger log = LoggerFactory.getLogger(IncrementVariableCommandExecutorService.class);

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, IncrementVariableCommand command) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> runTimeVariable = getRunTimeVariable(flowContext, command.getVariable());

        if (runTimeVariable instanceof IntegerRunTimeVariable integerRunTimeVariable) {

            final Integer newValue = Optional.of(integerRunTimeVariable)
                    .map(AbstractRunTimeVariable::getValue)
                    .map(value -> value + 1)
                    .orElse(1);

            flowContext.getVariables().put(command.getVariable().getName(), new IntegerRunTimeVariable(newValue));

        } else if (runTimeVariable instanceof LongRunTimeVariable longRunTimeVariable) {

            final Long newValue = Optional.of(longRunTimeVariable)
                    .map(AbstractRunTimeVariable::getValue)
                    .map(value -> value + 1)
                    .orElse(1L);

            flowContext.getVariables().put(command.getVariable().getName(), new LongRunTimeVariable(newValue));

        } else if (runTimeVariable instanceof BigDecimalRunTimeVariable bigDecimalRunTimeVariable) {

            final BigDecimal newValue = Optional.of(bigDecimalRunTimeVariable)
                    .map(AbstractRunTimeVariable::getValue)
                    .map(value -> value.add(new BigDecimal(1)))
                    .orElse(new BigDecimal(1));

            flowContext.getVariables().put(command.getVariable().getName(), new BigDecimalRunTimeVariable(newValue));

        } else {
            throw new CommandExecutionException("Unsupported variable type: '%s'. Supported types: Integer, Long, BigDecimal".formatted(runTimeVariable.getClass().getSimpleName()));
        }

    }

}
