package com.frogparser.flow.domain.find_by;

import jakarta.validation.constraints.NotBlank;

public class FindByCssSelector extends AbstractFindByValue {

    public FindByCssSelector() {
        super();
    }

    public FindByCssSelector(String value) {
        super(value);
    }

    @NotBlank
    @Override
    public String getValue() {
        return super.getValue();
    }

}
