package com.frogparser.flow.executor.service.command.find_element;

import com.frogparser.flow.domain.command.find_element.FindChildElementCommand;
import com.frogparser.flow.domain.runtime_variable.WebElementRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.SeleniumFindByService;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class FindChildElementCommandExecutorService extends AbstractCommandExecutorService<FindChildElementCommand> {

    private final SeleniumFindByService seleniumFindByService;

    public FindChildElementCommandExecutorService(SeleniumFindByService seleniumFindByService) {
        this.seleniumFindByService = seleniumFindByService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, FindChildElementCommand command) throws CommandExecutionException {
        final String variableName = getNotBlankVariableName(command::getVariable);
        final WebElement parentWebElement = getRunTimeVariableValue(flowContext, command.getParentElementVariable(), WebElementRunTimeVariable.class);
        final WebElement webElement = parentWebElement.findElement(seleniumFindByService.getSeleniumFindBy(command.getFindBy()));
        getContextVariables(flowContext).put(variableName, new WebElementRunTimeVariable(webElement));
    }
}
