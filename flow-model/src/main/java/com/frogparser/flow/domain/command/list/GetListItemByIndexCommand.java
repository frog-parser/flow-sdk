package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class GetListItemByIndexCommand extends AbstractListCommand {

    private FlowVariable indexVariable;
    private FlowVariable itemVariable;

    @Valid
    @NotNull
    public FlowVariable getIndexVariable() {
        return indexVariable;
    }

    public GetListItemByIndexCommand setIndexVariable(FlowVariable indexVariable) {
        this.indexVariable = indexVariable;
        return this;
    }

    @Valid
    @NotNull
    public FlowVariable getItemVariable() {
        return itemVariable;
    }

    public GetListItemByIndexCommand setItemVariable(FlowVariable itemVariable) {
        this.itemVariable = itemVariable;
        return this;
    }
}
