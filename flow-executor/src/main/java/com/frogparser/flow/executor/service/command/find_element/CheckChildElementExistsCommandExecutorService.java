package com.frogparser.flow.executor.service.command.find_element;

import com.frogparser.flow.domain.command.find_element.CheckChildElementExistsCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.runtime_variable.WebElementRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.SeleniumFindByService;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckChildElementExistsCommandExecutorService extends AbstractCommandExecutorService<CheckChildElementExistsCommand> {

    private final SeleniumFindByService seleniumFindByService;

    public CheckChildElementExistsCommandExecutorService(SeleniumFindByService seleniumFindByService) {
        this.seleniumFindByService = seleniumFindByService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, CheckChildElementExistsCommand command) throws CommandExecutionException {
        final String variableName = getNotBlankVariableName(command::getVariable);
        final WebElement parentWebElement = getRunTimeVariableValue(flowContext, command.getParentElementVariable(), WebElementRunTimeVariable.class);
        final List<WebElement> webElements = parentWebElement.findElements(seleniumFindByService.getSeleniumFindBy(command.getFindBy()));
        getContextVariables(flowContext).put(variableName, new BooleanRunTimeVariable(!webElements.isEmpty()));
    }

}
