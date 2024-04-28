package com.frogparser.flow.executor.service.command.find_element;

import com.frogparser.flow.domain.command.find_element.FindElementsCommand;
import com.frogparser.flow.domain.runtime_variable.list.WebElementListRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.SeleniumFindByService;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindElementsCommandExecutorService extends AbstractCommandExecutorService<FindElementsCommand> {

    private final SeleniumFindByService seleniumFindByService;

    public FindElementsCommandExecutorService(SeleniumFindByService seleniumFindByService) {
        this.seleniumFindByService = seleniumFindByService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, FindElementsCommand command) throws CommandExecutionException {
        final String variableName = getVariableName(command::getVariable);
        final List<WebElement> webElements = getWebDriver(flowContext).findElements(seleniumFindByService.getSeleniumFindBy(command.getFindBy()));
        getContextVariables(flowContext).put(variableName, new WebElementListRunTimeVariable(webElements));
    }

}
