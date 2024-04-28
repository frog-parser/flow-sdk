package com.frogparser.flow.executor.domain.web_driver_settings;

import java.nio.file.Path;

public class LocalChromeWebDriverSettings extends AbstractWebDriverSettings {

    private Path driverExecutablePath;
    private Path executablePath;

    public Path getDriverExecutablePath() {
        return driverExecutablePath;
    }

    public LocalChromeWebDriverSettings setDriverExecutablePath(Path driverExecutablePath) {
        this.driverExecutablePath = driverExecutablePath;
        return this;
    }

    public Path getExecutablePath() {
        return executablePath;
    }

    public LocalChromeWebDriverSettings setExecutablePath(Path executablePath) {
        this.executablePath = executablePath;
        return this;
    }
}