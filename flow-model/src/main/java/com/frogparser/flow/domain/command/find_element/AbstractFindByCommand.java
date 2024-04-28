package com.frogparser.flow.domain.command.find_element;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.flow.domain.find_by.AbstractFindBy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public abstract class AbstractFindByCommand extends AbstractCommand {

    private AbstractFindBy findBy;

    @Valid
    @NotNull
    public AbstractFindBy getFindBy() {
        return findBy;
    }

    public AbstractFindByCommand setFindBy(AbstractFindBy findBy) {
        this.findBy = findBy;
        return this;
    }
}
