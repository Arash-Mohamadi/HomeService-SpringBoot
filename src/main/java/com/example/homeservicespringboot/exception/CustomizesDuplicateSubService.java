package com.example.homeservicespringboot.exception;

public class CustomizesDuplicateSubService extends RuntimeException{

    public CustomizesDuplicateSubService() {
    }

    public CustomizesDuplicateSubService(String message) {
        super(message);
    }

    public CustomizesDuplicateSubService(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizesDuplicateSubService(Throwable cause) {
        super(cause);
    }

    public CustomizesDuplicateSubService(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
