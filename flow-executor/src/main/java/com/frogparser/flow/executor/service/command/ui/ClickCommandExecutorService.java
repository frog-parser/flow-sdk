package com.frogparser.flow.executor.service.command.ui;

import com.frogparser.flow.domain.command.ui.ClickCommand;
import com.frogparser.flow.domain.FlowContext;
import com.frogparser.flow.exception.CommandExecutionException;
import com.frogparser.flow.executor.domain.FlowExecutionSettings;
import com.frogparser.flow.executor.service.command.AbstractCommandExecutorService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClickCommandExecutorService extends AbstractCommandExecutorService<ClickCommand> {

    @Override
    public void execute(FlowExecutionSettings flowExecutionSettings, FlowContext flowContext, ClickCommand command) throws CommandExecutionException {
        WebElement webElement = getContextVariableValue(flowContext, command.getVariable(), WebElement.class);
        click(flowContext, webElement, Optional.ofNullable(command.getClickIfInvisible()).orElse(true));
    }

    private void click(FlowContext flowContext, WebElement webElement, boolean clickIfInvisible) {

        final var executor = (JavascriptExecutor) getWebDriver(flowContext);
        executor.executeScript("arguments[0].click();", webElement);

//        if (webElement.isDisplayed()) {
//            webElement.click();
//        } else {
//            if (clickIfInvisible) {
//                final var executor = (JavascriptExecutor) getWebDriver(flowContext);
//                executor.executeScript("arguments[0].click();", webElement);
//            }
//        }

    }

}
