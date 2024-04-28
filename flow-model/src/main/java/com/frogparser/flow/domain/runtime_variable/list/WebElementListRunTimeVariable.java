package com.frogparser.flow.domain.runtime_variable.list;

import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementListRunTimeVariable extends AbstractListRunTimeVariable<WebElement> {

    public WebElementListRunTimeVariable() {
        super();
    }

    public WebElementListRunTimeVariable(List<WebElement> value) {
        super(value);
    }

}
