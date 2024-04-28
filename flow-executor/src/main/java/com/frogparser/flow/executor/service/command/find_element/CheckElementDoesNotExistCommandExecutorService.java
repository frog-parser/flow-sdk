package com.frogparser.flow.executor.service.command.find_element;

import com.frogparser.flow.domain.command.find_element.CheckElementDoesNotExistCommand;
import com.frogparser.flow.domain.runtime_variable.BooleanRunTimeVariable;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.SeleniumFindByService;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class CheckElementDoesNotExistCommandExecutorService extends AbstractCommandExecutorService<CheckElementDoesNotExistCommand> {

    private final SeleniumFindByService seleniumFindByService;

    public CheckElementDoesNotExistCommandExecutorService(SeleniumFindByService seleniumFindByService) {
        this.seleniumFindByService = seleniumFindByService;
    }

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, CheckElementDoesNotExistCommand command) throws CommandExecutionException {
        final String variableName = getNotBlankVariableName(command::getVariable);
        final Boolean doesNotExists = getWebDriver(flowContext).findElements(seleniumFindByService.getSeleniumFindBy(command::getFindBy)).isEmpty();
        getContextVariables(flowContext).put(variableName, new BooleanRunTimeVariable(doesNotExists));
    }

}
