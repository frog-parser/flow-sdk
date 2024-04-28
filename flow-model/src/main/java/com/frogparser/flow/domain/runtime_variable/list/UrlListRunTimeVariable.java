package com.frogparser.flow.domain.runtime_variable.list;

import java.net.URL;
import java.util.List;

public class UrlListRunTimeVariable extends AbstractListRunTimeVariable<URL> {

    public UrlListRunTimeVariable() {
        super();
    }

    public UrlListRunTimeVariable(List<URL> value) {
        super(value);
    }

}
