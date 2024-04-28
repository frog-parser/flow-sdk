package com.frogparser.flow.domain.element_property;

import jakarta.validation.constraints.NotBlank;

public class ElementPropertyCssValue extends AbstractElementProperty {

    private String propertyName;

    public ElementPropertyCssValue() {
    }

    public ElementPropertyCssValue(String propertyName) {
        this.propertyName = propertyName;
    }

    @NotBlank
    public String getPropertyName() {
        return propertyName;
    }

    public ElementPropertyCssValue setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }
}
