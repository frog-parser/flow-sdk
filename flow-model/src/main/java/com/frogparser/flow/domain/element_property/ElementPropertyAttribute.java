package com.frogparser.flow.domain.element_property;

import jakarta.validation.constraints.NotBlank;

public class ElementPropertyAttribute extends AbstractElementProperty {

    private String name;

    public ElementPropertyAttribute() {

    }

    public ElementPropertyAttribute(String name) {
        this.name = name;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public ElementPropertyAttribute setName(String name) {
        this.name = name;
        return this;
    }
}
