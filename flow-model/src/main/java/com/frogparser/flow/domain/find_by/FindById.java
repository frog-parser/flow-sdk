package com.frogparser.flow.domain.find_by;

import jakarta.validation.constraints.NotBlank;

public class FindById extends AbstractFindByValue {

    public FindById() {
        super();
    }

    public FindById(String value) {
        super(value);
    }

    @NotBlank
    @Override
    public String getValue() {
        return super.getValue();
    }

}