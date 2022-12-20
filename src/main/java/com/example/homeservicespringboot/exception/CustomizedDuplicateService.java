package com.example.homeservicespringboot.exception;

public class CustomizedDuplicateService extends RuntimeException{
    public CustomizedDuplicateService() {
    }

    public CustomizedDuplicateService(String message) {
        super(message);
    }

    public CustomizedDuplicateService(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizedDuplicateService(Throwable cause) {
        super(cause);
    }

    public CustomizedDuplicateService(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
