package com.pageupcomputers.bolComWebhookReceiver.Exceptions;

/**
 * Custom Exception which will be throwed when the Bol.com order is not found
 */
public class BolComOrderNotFoundException extends RuntimeException {
    public BolComOrderNotFoundException(String message) {
        super(message);
    }   

    public BolComOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
