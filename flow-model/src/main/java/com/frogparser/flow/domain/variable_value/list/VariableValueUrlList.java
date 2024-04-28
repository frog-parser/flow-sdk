package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.net.URL;
import java.util.List;

public class VariableValueUrlList extends AbstractVariableValueList<URL> {

    public VariableValueUrlList() {
        super();
    }

    public VariableValueUrlList(List<URL> value) {
        super(value);
    }
}
