package com.frogparser.flow.executor.service.command.manage.window;

import com.frogparser.flow.domain.command.manage.window.SetWindowSizeCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.Dimension;
import org.springframework.stereotype.Service;

@Service
public class SetWindowSizeCommandExecutorService extends AbstractCommandExecutorService<SetWindowSizeCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, SetWindowSizeCommand command) throws CommandExecutionException {
        final Dimension dimension = getContextVariableValue(flowContext, command.getVariable(), Dimension.class);
        validateWebDriver(flowContext).manage().window().setSize(dimension);
    }

}
