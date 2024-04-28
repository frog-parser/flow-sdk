package com.frogparser.flow.domain.command.variable.set_variable_value;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.variable.FlowVariable;
import com.frogparser.flow.domain.variable_value.AbstractVariableValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SetConstantValueToVariableCommand extends AbstractCommand {

    private SetConstantValueToVariableTypeEnum variableType;
    private FlowVariable variable;
    private AbstractVariableValue<?> value;

    @NotNull
    public SetConstantValueToVariableTypeEnum getVariableType() {
        return variableType;
    }

    public SetConstantValueToVariableCommand setVariableType(SetConstantValueToVariableTypeEnum variableType) {
        this.variableType = variableType;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public SetConstantValueToVariableCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public AbstractVariableValue<?> getValue() {
        return value;
    }

    public SetConstantValueToVariableCommand setValue(AbstractVariableValue<?> value) {
        this.value = value;
        return this;
    }
}
