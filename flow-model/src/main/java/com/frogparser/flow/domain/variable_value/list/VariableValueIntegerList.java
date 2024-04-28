package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.util.List;

public class VariableValueIntegerList extends AbstractVariableValueList<Integer> {

    public VariableValueIntegerList() {
        super();
    }

    public VariableValueIntegerList(List<Integer> value) {
        super(value);
    }
}
