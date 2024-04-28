package com.frogparser.flow.domain.find_by;

import jakarta.validation.constraints.NotBlank;

public class FindByClassName extends AbstractFindByValue {

    public FindByClassName() {
        super();
    }

    public FindByClassName(String value) {
        super(value);
    }

    @NotBlank
    @Override
    public String getValue() {
        return super.getValue();
    }

}
