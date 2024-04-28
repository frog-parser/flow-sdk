package com.frogparser.flow.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class FlowContentToUpdate {

    private Long flowId;
    private FlowContent flowContent;

    @NotNull
    public Long getFlowId() {
        return flowId;
    }

    public FlowContentToUpdate setFlowId(Long flowId) {
        this.flowId = flowId;
        return this;
    }

    @Valid
    @NotNull
    public FlowContent getFlowContent() {
        return flowContent;
    }

    public FlowContentToUpdate setFlowContent(FlowContent flowContent) {
        this.flowContent = flowContent;
        return this;
    }
}
