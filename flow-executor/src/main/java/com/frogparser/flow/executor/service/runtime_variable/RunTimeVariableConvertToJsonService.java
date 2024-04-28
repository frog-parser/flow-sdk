package com.frogparser.flow.executor.service.runtime_variable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frogparser.common.exception.ApplicationException;
import com.frogparser.flow.domain.runtime_variable.AbstractRunTimeVariable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RunTimeVariableConvertToJsonService {

    public byte[] convert(final AbstractRunTimeVariable<?> runTimeVariable) {
        Objects.requireNonNull(runTimeVariable);

        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(runTimeVariable);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(String.format("Cannot export variable in JSON format: %s", e.getMessage()), e);
        }

    }

}
