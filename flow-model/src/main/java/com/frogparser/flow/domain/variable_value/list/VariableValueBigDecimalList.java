package com.frogparser.flow.domain.variable_value.list;

import com.frogparser.flow.domain.variable_value.AbstractVariableValueList;

import java.math.BigDecimal;
import java.util.List;

public class VariableValueBigDecimalList extends AbstractVariableValueList<BigDecimal> {

    public VariableValueBigDecimalList() {
        super();
    }

    public VariableValueBigDecimalList(List<BigDecimal> value) {
        super(value);
    }
}
