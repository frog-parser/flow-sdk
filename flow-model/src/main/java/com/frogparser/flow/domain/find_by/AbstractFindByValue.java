package com.frogparser.flow.domain.find_by;

public abstract class AbstractFindByValue extends AbstractFindBy {

    private String value;

    public AbstractFindByValue() {
        super();
    }

    public AbstractFindByValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public AbstractFindByValue setValue(String value) {
        this.value = value;
        return this;
    }
}
