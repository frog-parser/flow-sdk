package com.frogparser.flow.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class FlowContentHistoryToCreate {

    private Long flowId;
    private FlowContent flowContent;

    @NotNull
    public Long getFlowId() {
        return flowId;
    }

    public FlowContentHistoryToCreate setFlowId(Long flowId) {
        this.flowId = flowId;
        return this;
    }

    @NotNull
    @Valid
    public FlowContent getFlowContent() {
        return flowContent;
    }

    public FlowContentHistoryToCreate setFlowContent(FlowContent flowContent) {
        this.flowContent = flowContent;
        return this;
    }
}
