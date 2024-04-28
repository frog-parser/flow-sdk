package com.frogparser.flow.domain.element_property;

import jakarta.validation.constraints.NotBlank;

public class ElementPropertyDomProperty extends AbstractElementProperty {

    private String name;

    public ElementPropertyDomProperty() {

    }

    public ElementPropertyDomProperty(String name) {
        this.name = name;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public ElementPropertyDomProperty setName(String name) {
        this.name = name;
        return this;
    }
}
