package com.frogparser.flow.executor.service.command.manage.timeouts.sleep;

import com.frogparser.flow.domain.command.manage.timeouts.SleepCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.common.exception.ValidationException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SleepCommandExecutorService extends AbstractCommandExecutorService<SleepCommand> {

    private final static Long TIMOUT_LIMIT = 60000L;

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SleepCommand command) throws CommandExecutionException {

        final Long timout = getContextVariableValue(flowContext, command.getVariable(), Long.class);

        if (timout > TIMOUT_LIMIT) {
            throw new ValidationException("Sleep command variable '%s' value is limited with: %d"
                    .formatted(command.getVariable().getName(), TIMOUT_LIMIT));
        }

        try {
            TimeUnit.MILLISECONDS.sleep(timout);
        } catch (InterruptedException e) {
            doNothing();
        }

    }

    private void doNothing() {

    }

}
