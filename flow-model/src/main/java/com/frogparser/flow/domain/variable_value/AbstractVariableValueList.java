package com.frogparser.flow.domain.variable_value;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public abstract class AbstractVariableValueList<T> extends AbstractVariableValue<List<T>> {

    public AbstractVariableValueList() {
        super();
    }

    public AbstractVariableValueList(List<T> value) {
        super(value);
    }

    @Valid
    @NotNull
    @Override
    public List<@Valid @NotNull T> getValue() {
        return super.getValue();
    }

}
