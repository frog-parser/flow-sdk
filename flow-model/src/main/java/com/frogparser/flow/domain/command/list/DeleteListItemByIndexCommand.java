package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DeleteListItemByIndexCommand extends AbstractListCommand {

    private FlowVariable indexVariable;

    @Valid
    @NotNull
    public FlowVariable getIndexVariable() {
        return indexVariable;
    }

    public DeleteListItemByIndexCommand setIndexVariable(FlowVariable indexVariable) {
        this.indexVariable = indexVariable;
        return this;
    }
}
