package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.util.List;

public class VariableValueLongList extends AbstractVariableValueList<Long> {

    public VariableValueLongList() {
        super();
    }

    public VariableValueLongList(List<Long> value) {
        super(value);
    }
}
