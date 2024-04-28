package com.frogparser.flow.domain.dataset;

import com.frogparser.flow.domain.dataset_metadata.DatasetMetadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Dataset {

    private String variableName;
    private DatasetMetadata metadata;
    private List<Row> rows;

    @NotBlank
    public String getVariableName() {
        return variableName;
    }

    public Dataset setVariableName(String variableName) {
        this.variableName = variableName;
        return this;
    }

    @Valid
    @NotNull
    public DatasetMetadata getMetadata() {
        return metadata;
    }

    public Dataset setMetadata(DatasetMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid @NotNull Row> getRows() {
        return rows;
    }

    public Dataset setRows(List<Row> rows) {
        this.rows = rows;
        return this;
    }
}
