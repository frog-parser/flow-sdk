package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.util.List;

public class VariableValueStringList extends AbstractVariableValueList<String> {

    public VariableValueStringList() {
        super();
    }

    public VariableValueStringList(List<String> value) {
        super(value);
    }
}
