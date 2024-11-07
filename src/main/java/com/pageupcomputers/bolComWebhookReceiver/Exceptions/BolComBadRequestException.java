package com.pageupcomputers.bolComWebhookReceiver.Exceptions;

/**
 * Custom Exception which will be throwed when the Bol.com throws an Bad Request error
 */
public class BolComBadRequestException extends RuntimeException {
    public BolComBadRequestException(String message) {
        super(message);
    }   

    public BolComBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
