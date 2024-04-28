package com.frogparser.flow.domain.find_by;

import jakarta.validation.constraints.NotBlank;

public class FindByXPath extends AbstractFindByValue {

    public FindByXPath() {
        super();
    }

    public FindByXPath(String value) {
        super(value);
    }

    @NotBlank
    @Override
    public String getValue() {
        return super.getValue();
    }
}