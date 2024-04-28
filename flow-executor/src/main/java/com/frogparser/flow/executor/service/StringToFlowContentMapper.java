package com.frogparser.flow.executor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frogparser.flow.domain.FlowContent;
import com.frogparser.common.exception.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StringToFlowContentMapper {

    public FlowContent map(String flowContentAsString) {

        final ObjectMapper objectMapper = new ObjectMapper();

        final FlowContent result;

        if (Objects.nonNull(flowContentAsString) && !flowContentAsString.isBlank()) {
            try {

                final FlowContent flowContent = objectMapper.readValue(flowContentAsString, FlowContent.class);

                if (Objects.nonNull(flowContent.getCommands())) {
                    result = flowContent;
                } else {
                    result = new FlowContent().setCommands(List.of());
                }

            } catch (JsonProcessingException e) {
                throw new ApplicationException("Can not deserialize flow content: '%s'".formatted(e.getMessage()), e);
            }
        } else {
            result = new FlowContent().setCommands(List.of());
        }

        return result;

    }

}
