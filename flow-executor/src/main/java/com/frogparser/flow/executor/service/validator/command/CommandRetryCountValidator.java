package com.frogparser.flow.executor.service.validator.command;

import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandRetryCountValidator {

    public void validate(Integer retryCount) {

        if (Optional.ofNullable(retryCount).isEmpty()) {
            throw new ValidationException("Command retry count can not be empty");
        }

    }

}
