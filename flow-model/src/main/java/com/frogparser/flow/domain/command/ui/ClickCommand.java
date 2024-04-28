package com.frogparser.flow.domain.command.ui;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ClickCommand extends AbstractCommand {

    private FlowVariable variable;
    private Boolean clickIfInvisible = true;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public ClickCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @NotNull
    public Boolean getClickIfInvisible() {
        return clickIfInvisible;
    }

    public ClickCommand setClickIfInvisible(Boolean clickIfInvisible) {
        this.clickIfInvisible = clickIfInvisible;
        return this;
    }
}
