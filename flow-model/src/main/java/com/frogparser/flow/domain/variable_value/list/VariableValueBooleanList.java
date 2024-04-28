package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.util.List;

public class VariableValueBooleanList extends AbstractVariableValueList<Boolean> {

    public VariableValueBooleanList() {
        super();
    }

    public VariableValueBooleanList(List<Boolean> value) {
        super(value);
    }
}
