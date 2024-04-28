package com.frogparser.flow.executor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class FlowExecution {

    private Long id;
    private LocalDateTime started;
    private LocalDateTime finished;
    private FlowExecutionStateEnum state;
    private String message;
    private String stackTrace;
    private List<RunTimeVariableHeader> variables;

    public Long getId() {
        return id;
    }

    public FlowExecution setId(Long id) {
        this.id = id;
        return this;
    }

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getStarted() {
        return started;
    }

    public FlowExecution setStarted(LocalDateTime started) {
        this.started = started;
        return this;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getFinished() {
        return finished;
    }

    public FlowExecution setFinished(LocalDateTime finished) {
        this.finished = finished;
        return this;
    }

    @NotNull
    public FlowExecutionStateEnum getState() {
        return state;
    }

    public FlowExecution setState(FlowExecutionStateEnum state) {
        this.state = state;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FlowExecution setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public FlowExecution setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    @Valid
    @NotNull
    public List<@Valid RunTimeVariableHeader> getVariables() {
        return variables;
    }

    public FlowExecution setVariables(List<RunTimeVariableHeader> variables) {
        this.variables = variables;
        return this;
    }
}
