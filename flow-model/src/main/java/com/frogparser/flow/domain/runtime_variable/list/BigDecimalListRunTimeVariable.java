package com.frogparser.flow.domain.runtime_variable.list;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalListRunTimeVariable extends AbstractListRunTimeVariable<BigDecimal> {

    public BigDecimalListRunTimeVariable() {
        super();
    }

    public BigDecimalListRunTimeVariable(List<BigDecimal> value) {
        super(value);
    }

}
