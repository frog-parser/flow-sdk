package com.frogparser.flow.domain;

import jakarta.validation.constraints.NotNull;

public class FlowToExecute {

    private Long flowId;

    @NotNull
    public Long getFlowId() {
        return flowId;
    }

    public FlowToExecute setFlowId(Long flowId) {
        this.flowId = flowId;
        return this;
    }
}
