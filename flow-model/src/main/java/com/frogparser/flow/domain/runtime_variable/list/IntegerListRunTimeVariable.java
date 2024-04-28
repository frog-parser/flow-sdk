package com.frogparser.flow.domain.runtime_variable.list;

import java.util.List;

public class IntegerListRunTimeVariable extends AbstractListRunTimeVariable<Integer> {

    public IntegerListRunTimeVariable() {
        super();
    }

    public IntegerListRunTimeVariable(List<Integer> value) {
        super(value);
    }

}
