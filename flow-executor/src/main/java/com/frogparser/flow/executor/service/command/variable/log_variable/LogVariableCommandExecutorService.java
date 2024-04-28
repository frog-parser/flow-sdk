package com.frogparser.flow.executor.service.command.variable.log_variable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frogparser.flow.domain.command.variable.log_variable.LogVariableCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogVariableCommandExecutorService extends AbstractCommandExecutorService<LogVariableCommand> {

    private static final Logger log = LoggerFactory.getLogger(LogVariableCommandExecutorService.class);

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, LogVariableCommand command) throws CommandExecutionException {

        ObjectMapper objectMapper = new ObjectMapper();

        final AbstractRunTimeVariable<?> runTimeVariable = getRunTimeVariable(flowContext, command.getVariable());

        try {
            final String jsonAsString = objectMapper.writeValueAsString(runTimeVariable);

            log.info(jsonAsString);

        } catch (JsonProcessingException e) {
            throw new CommandExecutionException("Can not log variable value: '%s'".formatted(e.getMessage()), e);
        }

    }
}
