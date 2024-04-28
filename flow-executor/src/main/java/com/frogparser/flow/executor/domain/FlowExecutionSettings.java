package com.frogparser.flow.executor.domain;

import jakarta.validation.constraints.PositiveOrZero;

public class FlowExecutionSettings {

    private Long flowExecutionTimeLimit;
    private Long maximumCommandExecutionsInFlowLimit;
    private Long maximumRecursionDepthLimit;
    private Long maximumRowsInListLimit;
    private Long maximumRowsInDatasetLimit;

    @PositiveOrZero
    public Long getFlowExecutionTimeLimit() {
        return flowExecutionTimeLimit;
    }

    public FlowExecutionSettings setFlowExecutionTimeLimit(Long flowExecutionTimeLimit) {
        this.flowExecutionTimeLimit = flowExecutionTimeLimit;
        return this;
    }

    @PositiveOrZero
    public Long getMaximumCommandExecutionsInFlowLimit() {
        return maximumCommandExecutionsInFlowLimit;
    }

    public FlowExecutionSettings setMaximumCommandExecutionsInFlowLimit(Long maximumCommandExecutionsInFlowLimit) {
        this.maximumCommandExecutionsInFlowLimit = maximumCommandExecutionsInFlowLimit;
        return this;
    }

    @PositiveOrZero
    public Long getMaximumRecursionDepthLimit() {
        return maximumRecursionDepthLimit;
    }

    public FlowExecutionSettings setMaximumRecursionDepthLimit(Long maximumRecursionDepthLimit) {
        this.maximumRecursionDepthLimit = maximumRecursionDepthLimit;
        return this;
    }

    @PositiveOrZero
    public Long getMaximumRowsInListLimit() {
        return maximumRowsInListLimit;
    }

    public FlowExecutionSettings setMaximumRowsInListLimit(Long maximumRowsInListLimit) {
        this.maximumRowsInListLimit = maximumRowsInListLimit;
        return this;
    }

    @PositiveOrZero
    public Long getMaximumRowsInDatasetLimit() {
        return maximumRowsInDatasetLimit;
    }

    public FlowExecutionSettings setMaximumRowsInDatasetLimit(Long maximumRowsInDatasetLimit) {
        this.maximumRowsInDatasetLimit = maximumRowsInDatasetLimit;
        return this;
    }
}
