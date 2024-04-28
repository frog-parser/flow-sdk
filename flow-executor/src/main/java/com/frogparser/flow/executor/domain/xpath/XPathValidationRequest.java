package com.frogparser.flow.executor.domain.xpath;

import jakarta.validation.constraints.NotBlank;

public class XPathValidationRequest {

    private String xpathExpression;

    @NotBlank
    public String getXpathExpression() {
        return xpathExpression;
    }

    public XPathValidationRequest setXpathExpression(String xpathExpression) {
        this.xpathExpression = xpathExpression;
        return this;
    }
}
