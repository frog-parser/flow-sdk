package com.frogparser.flow.domain.variable_value;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public abstract class AbstractVariableValueMap<K, V> extends AbstractVariableValue<Map<K, V>> {

    public AbstractVariableValueMap() {
        super();
    }

    public AbstractVariableValueMap(Map<K, V> value) {
        super(value);
    }

    @Valid
    @NotNull
    @Override
    public Map<@Valid @NotNull K, @Valid @NotNull V> getValue() {
        return super.getValue();
    }

}
