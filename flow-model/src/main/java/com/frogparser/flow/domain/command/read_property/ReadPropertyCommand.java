package com.frogparser.flow.domain.command.read_property;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.element_property.AbstractElementProperty;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ReadPropertyCommand extends AbstractCommand {

    private FlowVariable variable;
    private AbstractElementProperty property;
    private FlowVariable newVariable;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public ReadPropertyCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public AbstractElementProperty getProperty() {
        return property;
    }

    public ReadPropertyCommand setProperty(AbstractElementProperty property) {
        this.property = property;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getNewVariable() {
        return newVariable;
    }

    public ReadPropertyCommand setNewVariable(FlowVariable newVariable) {
        this.newVariable = newVariable;
        return this;
    }
}
