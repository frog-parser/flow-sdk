package com.frogparser.flow.executor.service.validator.command;

import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class CommandNameValidator {

    public void validate(String name) {

        if (Optional.ofNullable(name).filter(Predicate.not(String::isBlank)).isEmpty()) {
            throw new ValidationException("Command name can not be empty");
        }

    }

}
