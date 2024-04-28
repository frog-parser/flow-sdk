package com.frogparser.flow.executor.service.command.manage.timeouts.sleep;

import com.frogparser.flow.domain.command.manage.timeouts.SetScriptTimeoutCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.common.exception.ValidationException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

@Service
public class SetScriptTimeoutCommandExecutorService extends AbstractCommandExecutorService<SetScriptTimeoutCommand> {

    private final static Long TIMOUT_LIMIT = 60000L;

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SetScriptTimeoutCommand command) throws CommandExecutionException {
        final Long amount = getContextVariableValue(flowContext, command.getVariable(), Long.class);

        if (amount > TIMOUT_LIMIT) {
            throw new ValidationException("Set script timeout command variable '%s' value is limited with: %d"
                    .formatted(command.getVariable().getName(), TIMOUT_LIMIT));
        }

        validateWebDriver(flowContext).manage().timeouts().scriptTimeout(Duration.of(amount, MILLIS));
    }

}
