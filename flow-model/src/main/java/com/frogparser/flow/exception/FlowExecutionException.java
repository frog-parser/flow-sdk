package com.frogparser.flow.exception;

public class FlowExecutionException extends Exception {

    public FlowExecutionException() {
    }

    public FlowExecutionException(String message) {
        super(message);
    }

    public FlowExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowExecutionException(Throwable cause) {
        super(cause);
    }
}
