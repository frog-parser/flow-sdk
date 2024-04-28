package com.frogparser.flow.domain.element_property;

import jakarta.validation.constraints.NotBlank;

public class ElementPropertyDomAttribute extends AbstractElementProperty {

    private String name;

    public ElementPropertyDomAttribute() {

    }

    public ElementPropertyDomAttribute(String name) {
        this.name = name;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public ElementPropertyDomAttribute setName(String name) {
        this.name = name;
        return this;
    }
}
