package com.frogparser.flow.domain.runtime_variable.list;

import java.util.List;

public class StringListRunTimeVariable extends AbstractListRunTimeVariable<String> {

    public StringListRunTimeVariable() {
        super();
    }

    public StringListRunTimeVariable(List<String> value) {
        super(value);
    }

}
