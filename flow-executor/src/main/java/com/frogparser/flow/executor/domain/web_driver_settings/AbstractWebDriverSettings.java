package com.frogparser.flow.executor.domain.web_driver_settings;

import java.util.List;

public abstract class AbstractWebDriverSettings {
    private List<String> arguments;

    public List<String> getArguments() {
        return arguments;
    }

    public AbstractWebDriverSettings setArguments(List<String> arguments) {
        this.arguments = arguments;
        return this;
    }
}