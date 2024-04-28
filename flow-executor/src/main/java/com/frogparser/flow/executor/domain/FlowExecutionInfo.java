package com.frogparser.flow.executor.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class FlowExecutionInfo {

    private Long projectId;
    private Long flowId;
    private FlowExecution flowExecution;

    public FlowExecutionInfo() {

    }

    public FlowExecutionInfo(Long projectId, Long flowId, FlowExecution flowExecution) {
        this.projectId = projectId;
        this.flowId = flowId;
        this.flowExecution = flowExecution;
    }

    @NotNull
    public Long getProjectId() {
        return projectId;
    }

    public FlowExecutionInfo setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    @NotNull
    public Long getFlowId() {
        return flowId;
    }

    public FlowExecutionInfo setFlowId(Long flowId) {
        this.flowId = flowId;
        return this;
    }

    @Valid
    @NotNull
    public FlowExecution getFlowExecution() {
        return flowExecution;
    }

    public FlowExecutionInfo setFlowExecution(FlowExecution flowExecution) {
        this.flowExecution = flowExecution;
        return this;
    }
}
