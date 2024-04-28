package com.frogparser.flow.domain;

import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.util.Map;

public class FlowContext {

    private FlowExecutionId flowExecutionId;
    private LocalDateTime commandsExecutionFinishTime;
    private LocalDateTime commandsExecutionStartTime;
    private LocalDateTime executionFinishTime;
    private LocalDateTime executionStartTime;
    private Long commandExecutionsInFlow;
    private Long recursionDepth;
    private Map<String, AbstractRunTimeVariable<?>> variables;
    private WebDriver webDriver;

    public FlowExecutionId getFlowExecutionId() {
        return flowExecutionId;
    }

    public FlowContext setFlowExecutionId(FlowExecutionId flowExecutionId) {
        this.flowExecutionId = flowExecutionId;
        return this;
    }

    public LocalDateTime getCommandsExecutionFinishTime() {
        return commandsExecutionFinishTime;
    }

    public FlowContext setCommandsExecutionFinishTime(LocalDateTime commandsExecutionFinishTime) {
        this.commandsExecutionFinishTime = commandsExecutionFinishTime;
        return this;
    }

    public LocalDateTime getCommandsExecutionStartTime() {
        return commandsExecutionStartTime;
    }

    public FlowContext setCommandsExecutionStartTime(LocalDateTime commandsExecutionStartTime) {
        this.commandsExecutionStartTime = commandsExecutionStartTime;
        return this;
    }

    public LocalDateTime getExecutionFinishTime() {
        return executionFinishTime;
    }

    public FlowContext setExecutionFinishTime(LocalDateTime executionFinishTime) {
        this.executionFinishTime = executionFinishTime;
        return this;
    }

    public LocalDateTime getExecutionStartTime() {
        return executionStartTime;
    }

    public FlowContext setExecutionStartTime(LocalDateTime executionStartTime) {
        this.executionStartTime = executionStartTime;
        return this;
    }

    public Long getCommandExecutionsInFlow() {
        return commandExecutionsInFlow;
    }

    public FlowContext setCommandExecutionsInFlow(Long commandExecutionsInFlow) {
        this.commandExecutionsInFlow = commandExecutionsInFlow;
        return this;
    }

    public Long getRecursionDepth() {
        return recursionDepth;
    }

    public FlowContext setRecursionDepth(Long recursionDepth) {
        this.recursionDepth = recursionDepth;
        return this;
    }

    public Map<String, AbstractRunTimeVariable<?>> getVariables() {
        return variables;
    }

    public FlowContext setVariables(Map<String, AbstractRunTimeVariable<?>> variables) {
        this.variables = variables;
        return this;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public FlowContext setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }
}
