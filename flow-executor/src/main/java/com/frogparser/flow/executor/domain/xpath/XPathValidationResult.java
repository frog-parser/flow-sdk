package com.frogparser.flow.executor.domain.xpath;

import jakarta.validation.constraints.NotNull;

public class XPathValidationResult {

    private Boolean valid;

    public XPathValidationResult() {
    }

    public XPathValidationResult(Boolean valid) {
        this.valid = valid;
    }

    @NotNull
    public Boolean getValid() {
        return valid;
    }

    public XPathValidationResult setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }
}
