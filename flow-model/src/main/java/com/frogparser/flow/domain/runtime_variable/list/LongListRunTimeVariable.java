package com.frogparser.flow.domain.runtime_variable.list;

import java.util.List;

public class LongListRunTimeVariable extends AbstractListRunTimeVariable<Long> {

    public LongListRunTimeVariable() {
        super();
    }

    public LongListRunTimeVariable(List<Long> value) {
        super(value);
    }

}
