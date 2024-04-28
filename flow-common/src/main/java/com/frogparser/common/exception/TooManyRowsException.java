package com.frogparser.common.exception;

public class TooManyRowsException extends ApplicationException {

    public TooManyRowsException() {
    }

    public TooManyRowsException(String message) {
        super(message);
    }

    public TooManyRowsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyRowsException(Throwable cause) {
        super(cause);
    }

}
