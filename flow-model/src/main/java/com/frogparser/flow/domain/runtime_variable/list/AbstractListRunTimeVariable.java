package com.frogparser.flow.domain.runtime_variable.list;

import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;

import java.util.List;

public abstract class AbstractListRunTimeVariable<T> extends AbstractRunTimeVariable<List<T>> {

    public AbstractListRunTimeVariable() {
        super();
    }

    public AbstractListRunTimeVariable(List<T> value) {
        super(value);
    }

}
