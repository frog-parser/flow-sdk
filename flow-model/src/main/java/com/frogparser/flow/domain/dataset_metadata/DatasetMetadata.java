package com.frogparser.flow.domain.dataset_metadata;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DatasetMetadata {
    private List<DatasetMetadataColumn> columns;

    @Valid
    @NotNull
    @NotEmpty
    public List<@Valid @NotNull DatasetMetadataColumn> getColumns() {
        return columns;
    }

    public DatasetMetadata setColumns(List<DatasetMetadataColumn> columns) {
        this.columns = columns;
        return this;
    }
}
