package com.frogparser.flow.domain;

public class FlowContentHistory {

    private Long flowId;
    private FlowContent flowContent;

    public FlowContentHistory() {

    }

    public FlowContentHistory(Long flowId, FlowContent flowContent) {
        this.flowId = flowId;
        this.flowContent = flowContent;
    }

    public Long getFlowId() {
        return flowId;
    }

    public FlowContentHistory setFlowId(Long flowId) {
        this.flowId = flowId;
        return this;
    }

    public FlowContent getFlowContent() {
        return flowContent;
    }

    public FlowContentHistory setFlowContent(FlowContent flowContent) {
        this.flowContent = flowContent;
        return this;
    }
}
