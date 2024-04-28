package com.frogparser.flow.executor.service.validator;

import com.frogparser.flow.domain.FlowExecutionId;
import com.frogparser.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlowExecutionIdValidator {

    public void validate(FlowExecutionId flowExecutionId) {

        if (Objects.isNull(flowExecutionId)) {
            throw new ValidationException("Flow execution id can not be empty.");
        }

        validate(flowExecutionId.getId());

    }

    public void validate(Long id) {

        if (Objects.isNull(id)) {
            throw new ValidationException("Flow execution id can not be empty.");
        }

    }

}
