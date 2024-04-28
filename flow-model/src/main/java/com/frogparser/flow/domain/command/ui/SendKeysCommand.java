package com.frogparser.flow.domain.command.ui;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SendKeysCommand extends AbstractCommand {

    private FlowVariable webElementVariable;
    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getWebElementVariable() {
        return webElementVariable;
    }

    public SendKeysCommand setWebElementVariable(FlowVariable webElementVariable) {
        this.webElementVariable = webElementVariable;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public SendKeysCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
