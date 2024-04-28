package com.frogparser.flow.executor.domain;

import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RunTimeVariable {

    private RunTimeVariableHeader header;
    private AbstractRunTimeVariable<?> body;

    public RunTimeVariable() {
    }

    public RunTimeVariable(RunTimeVariableHeader header, AbstractRunTimeVariable<?> body) {
        this.header = header;
        this.body = body;
    }

    @Valid
    @NotNull
    public RunTimeVariableHeader getHeader() {
        return header;
    }

    public RunTimeVariable setHeader(RunTimeVariableHeader header) {
        this.header = header;
        return this;
    }

    @Valid
    @NotNull
    public AbstractRunTimeVariable<?> getBody() {
        return body;
    }

    public RunTimeVariable setBody(AbstractRunTimeVariable<?> body) {
        this.body = body;
        return this;
    }
}
