package com.frogparser.flow.domain.dataset_metadata;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DatasetMetadataColumn {

    private String name;
    private DatasetMetadataColumnTypeEnum type;

    @NotBlank
    public String getName() {
        return name;
    }

    public DatasetMetadataColumn setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public DatasetMetadataColumnTypeEnum getType() {
        return type;
    }

    public DatasetMetadataColumn setType(DatasetMetadataColumnTypeEnum type) {
        this.type = type;
        return this;
    }
}
