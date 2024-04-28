package com.frogparser.flow.executor.service.validator.command;

import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandEnabledValidator {

    public void validate(Boolean enabled) {

        if (Optional.ofNullable(enabled).isEmpty()) {
            throw new ValidationException("Command enabled can not be empty");
        }

    }

}
