package com.frogparser.flow.domain;

import com.frogparser.flow.domain.command.AbstractCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FlowContent {

    private FlowContentVersionEnum version;
    private List<String> description;
    private String acceptLanguage;
    private List<AbstractCommand> commands;

    public FlowContent() {
    }

    @NotNull
    public FlowContentVersionEnum getVersion() {
        return version;
    }

    public FlowContent setVersion(FlowContentVersionEnum version) {
        this.version = version;
        return this;
    }

    public List<String> getDescription() {
        return description;
    }

    public FlowContent setDescription(List<String> description) {
        this.description = description;
        return this;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public FlowContent setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull AbstractCommand> getCommands() {
        return commands;
    }

    public FlowContent setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
        return this;
    }
}
