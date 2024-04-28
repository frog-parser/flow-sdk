package com.frogparser.flow.domain.command.dataset;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.dataset_metadata.DatasetMetadata;
import com.frogparser.flow.domain.variable.FlowVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreateDatasetCommand extends AbstractCommand {

    private FlowVariable variable;
    private DatasetMetadata metadata;

    @Valid
    @NotNull
    public FlowVariable getVariable() {
        return variable;
    }

    public CreateDatasetCommand setVariable(FlowVariable variable) {
        this.variable = variable;
        return this;
    }

    @Valid
    @NotNull
    public DatasetMetadata getMetadata() {
        return metadata;
    }

    public CreateDatasetCommand setMetadata(DatasetMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
