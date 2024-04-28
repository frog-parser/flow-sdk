package com.frogparser.flow.domain.runtime_variable.list;

import com.frogparser.flow.domain.runtime_variable.CustomWebElement;

import java.util.List;

public class CustomWebElementListRunTimeVariable extends AbstractListRunTimeVariable<CustomWebElement> {

    public CustomWebElementListRunTimeVariable() {
        super();
    }

    public CustomWebElementListRunTimeVariable(List<CustomWebElement> value) {
        super(value);
    }

}
