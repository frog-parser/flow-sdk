package com.frogparser.flow.executor.service.command.ui;

import com.frogparser.flow.domain.command.ui.GoBackCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.springframework.stereotype.Service;

@Service
public class GoBackCommandExecutorService extends AbstractCommandExecutorService<GoBackCommand> {
    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, GoBackCommand command) throws CommandExecutionException {
        getWebDriver(flowContext).navigate().back();
    }
}
