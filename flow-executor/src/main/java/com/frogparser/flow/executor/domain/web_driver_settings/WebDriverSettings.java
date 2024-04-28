package com.frogparser.flow.executor.domain.web_driver_settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "frog.selenium.web-driver")
public class WebDriverSettings {

    private WebDriverTypeEnum type;
    private LocalChromeWebDriverSettings localChrome;
    private RemoteChromeWebDriverSettings remoteChrome;
    private Window window;
    private Timeouts timeouts;

    public WebDriverTypeEnum getType() {
        return type;
    }

    public WebDriverSettings setType(WebDriverTypeEnum type) {
        this.type = type;
        return this;
    }

    public LocalChromeWebDriverSettings getLocalChrome() {
        return localChrome;
    }

    public WebDriverSettings setLocalChrome(LocalChromeWebDriverSettings localChrome) {
        this.localChrome = localChrome;
        return this;
    }

    public RemoteChromeWebDriverSettings getRemoteChrome() {
        return remoteChrome;
    }

    public WebDriverSettings setRemoteChrome(RemoteChromeWebDriverSettings remoteChrome) {
        this.remoteChrome = remoteChrome;
        return this;
    }

    public Window getWindow() {
        return window;
    }

    public WebDriverSettings setWindow(Window window) {
        this.window = window;
        return this;
    }

    public Timeouts getTimeouts() {
        return timeouts;
    }

    public WebDriverSettings setTimeouts(Timeouts timeouts) {
        this.timeouts = timeouts;
        return this;
    }
}