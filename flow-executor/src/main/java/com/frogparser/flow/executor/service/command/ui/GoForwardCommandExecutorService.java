package com.frogparser.flow.executor.service.command.ui;

import com.frogparser.flow.domain.command.ui.GoForwardCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class GoForwardCommandExecutorService extends AbstractCommandExecutorService<GoForwardCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GoForwardCommand command) throws CommandExecutionException {
        getWebDriver(flowContext).navigate().forward();
    }
}
