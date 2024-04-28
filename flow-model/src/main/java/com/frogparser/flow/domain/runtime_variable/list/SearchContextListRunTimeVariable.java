package com.frogparser.flow.domain.runtime_variable.list;

import org.openqa.selenium.SearchContext;

import java.util.List;

public class SearchContextListRunTimeVariable extends AbstractListRunTimeVariable<SearchContext> {

    public SearchContextListRunTimeVariable() {
        super();
    }

    public SearchContextListRunTimeVariable(List<SearchContext> value) {
        super(value);
    }

}
