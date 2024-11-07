package com.pageupcomputers.bolComWebhookReceiver.Exceptions;

/**
 * Custom Exception which will be throwed when the Credentials from Bol.com are incorrect
 */
public class BolComBadCredentialsException extends RuntimeException {
    public BolComBadCredentialsException(String message) {
        super(message);
    }   

    public BolComBadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
