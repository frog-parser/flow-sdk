package com.frogparser.flow.executor.domain.web_driver_settings;

import java.net.URL;

public class RemoteChromeWebDriverSettings extends AbstractWebDriverSettings {

    private URL url;

    public URL getUrl() {
        return url;
    }

    public RemoteChromeWebDriverSettings setUrl(URL url) {
        this.url = url;
        return this;
    }
}
