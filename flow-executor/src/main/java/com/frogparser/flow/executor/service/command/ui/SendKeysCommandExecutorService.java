package com.frogparser.flow.executor.service.command.ui;

import com.frogparser.flow.domain.command.ui.SendKeysCommand;
import com.frogparser.flow.domain.runtime_variable.StringRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.WebElementRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class SendKeysCommandExecutorService extends AbstractCommandExecutorService<SendKeysCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SendKeysCommand command) throws CommandExecutionException {
        final WebElement webElement = getRunTimeVariableValue(flowContext, command.getWebElementVariable(), WebElementRunTimeVariable.class);
        webElement.sendKeys(getRunTimeVariableValue(flowContext, command.getVariable(), StringRunTimeVariable.class));
    }
}
