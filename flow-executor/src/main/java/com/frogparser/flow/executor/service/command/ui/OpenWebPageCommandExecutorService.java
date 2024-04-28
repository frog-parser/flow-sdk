package com.frogparser.flow.executor.service.command.ui;

import com.frogparser.flow.domain.command.ui.OpenWebPageCommand;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.UrlRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;

@Service
public class OpenWebPageCommandExecutorService extends AbstractCommandExecutorService<OpenWebPageCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, OpenWebPageCommand command) throws CommandExecutionException {

        final AbstractRunTimeVariable<?> variable = flowContext
                .getVariables()
                .get(command.getVariable().getName());

        if (Objects.nonNull(variable)) {

            if (variable instanceof UrlRunTimeVariable urlRunTimeVariable) {

                final String url = Optional.ofNullable(urlRunTimeVariable.getValue())
                        .map(URL::toString)
                        .orElseThrow(() -> new CommandExecutionException("Command url can not be empty"));

                final WebDriver webDriver = validateWebDriver(flowContext);
                webDriver.get(url);

            } else {
                throw new CommandExecutionException("Variable has incorrect type: '%s'".formatted(variable.getClass().getSimpleName()));
            }

        } else {
            throw new CommandExecutionException("Variable can not be empty");
        }

    }

}
