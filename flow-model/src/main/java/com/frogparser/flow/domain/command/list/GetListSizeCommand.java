package com.frogparser.flow.domain.command.list;

import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class GetListSizeCommand extends AbstractListCommand {

    private FlowVariable sizeVariable;

    @Valid
    @NotNull
    public FlowVariable getSizeVariable() {
        return sizeVariable;
    }

    public GetListSizeCommand setSizeVariable(FlowVariable sizeVariable) {
        this.sizeVariable = sizeVariable;
        return this;
    }
}
