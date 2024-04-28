package com.frogparser.flow.executor.service.validator.command;

import com.frogparser.flow.domain.command.AbstractCommand;
import com.frogparser.common.exception.ValidationException;

import java.util.Objects;

public class AbstractCommandUpdateValidator {

    private final CommandNameValidator commandNameValidator;
    private final CommandRetryCountValidator commandRetryCountValidator;

    public AbstractCommandUpdateValidator(
            CommandNameValidator commandNameValidator,
            CommandRetryCountValidator commandRetryCountValidator) {
        this.commandNameValidator = commandNameValidator;
        this.commandRetryCountValidator = commandRetryCountValidator;
    }

    public void validate(AbstractCommand commandToUpdate) {

        if (Objects.isNull(commandToUpdate)) {
            throw new ValidationException("Command can not be empty");
        }

        commandNameValidator.validate(commandToUpdate.getName());
        commandRetryCountValidator.validate(commandToUpdate.getRetryCount());

    }

}
