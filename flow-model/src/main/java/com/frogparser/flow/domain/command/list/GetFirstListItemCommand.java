package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class GetFirstListItemCommand extends AbstractListCommand {

    private FlowVariable itemVariable;

    @Valid
    @NotNull
    public FlowVariable getItemVariable() {
        return itemVariable;
    }

    public GetFirstListItemCommand setItemVariable(FlowVariable itemVariable) {
        this.itemVariable = itemVariable;
        return this;
    }
}
