package com.frogparser.flow.domain.command.find_element;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class FindChildElementCommand extends AbstractFindByCommand {

    private FlowVariable variable;
    private FlowVariable parentElementVariable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public FindChildElementCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getParentElementVariable() {
        return parentElementVariable;
    }

    public FindChildElementCommand setParentElementVariable(FlowVariable parentElementVariable) {
        this.parentElementVariable = parentElementVariable;
        return this;
    }
}
