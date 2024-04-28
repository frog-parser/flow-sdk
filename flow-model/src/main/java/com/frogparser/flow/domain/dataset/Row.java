package com.frogparser.flow.domain.dataset;

import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class Row {

    private Map<String, AbstractRunTimeVariable<?>> columns;

    @NotNull
    public Map<@NotBlank String, @Valid @NotNull AbstractRunTimeVariable<?>> getColumns() {
        return columns;
    }

    public Row setColumns(Map<String, AbstractRunTimeVariable<?>> columns) {
        this.columns = columns;
        return this;
    }
}
