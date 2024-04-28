package com.frogparser.flow.executor.service.command.find_element;

import com.frogparser.flow.domain.command.find_element.FindElementCommand;
import com.frogparser.flow.domain.runtime_variable.WebElementRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.SeleniumFindByService;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class FindElementCommandExecutorService extends AbstractCommandExecutorService<FindElementCommand> {

    private final SeleniumFindByService seleniumFindByService;

    public FindElementCommandExecutorService(SeleniumFindByService seleniumFindByService) {
        this.seleniumFindByService = seleniumFindByService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, FindElementCommand command) throws CommandExecutionException {
        final String variableName = getVariableName(command::getVariable);
        final WebElement webElement = getWebDriver(flowContext).findElement(seleniumFindByService.getSeleniumFindBy(command::getFindBy));
        getContextVariables(flowContext).put(variableName, new WebElementRunTimeVariable(webElement));
    }

}
