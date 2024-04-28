package com.frogparser.flow.executor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frogparser.flow.domain.FlowContent;
import com.frogparser.common.exception.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FlowContentToStringMapper {

    public String map(final FlowContent flowContent) {

        final ObjectMapper objectMapper = new ObjectMapper();

        final String flowContentAsString;

        if (Objects.nonNull(flowContent)) {
            try {
                flowContentAsString = objectMapper.writeValueAsString(flowContent);
            } catch (JsonProcessingException e) {
                throw new ApplicationException("Can not serialize flow content: '%s'".formatted(e.getMessage()), e);
            }
        } else {
            flowContentAsString = this.map(new FlowContent().setCommands(List.of()));
        }

        return flowContentAsString;

    }

}
