package com.frogparser.flow.domain.command.find_element;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class FindElementsCommand extends AbstractFindByCommand {

    private FlowVariable variable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public FindElementsCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }
}
