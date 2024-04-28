package com.frogparser.flow.executor.service.validator.command;

import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandRunInOrderValidator {

    public void validate(Integer runInOrder) {

        if (Optional.ofNullable(runInOrder).isEmpty()) {
            throw new ValidationException("Command run in order can not be empty");
        }

    }

}
