package com.frogparser.flow.domain.runtime_variable;

import org.openqa.selenium.WebElement;

public class WebElementRunTimeVariable extends AbstractRunTimeVariable<WebElement> {

    public WebElementRunTimeVariable() {
        super();
    }

    public WebElementRunTimeVariable(WebElement value) {
        super(value);
    }

}
