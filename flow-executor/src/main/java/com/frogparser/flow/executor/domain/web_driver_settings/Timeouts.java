package com.frogparser.flow.executor.domain.web_driver_settings;

public record Timeouts(Integer pageLoadTimeout, Integer scriptTimeout, Integer implicitlyWait) {

}