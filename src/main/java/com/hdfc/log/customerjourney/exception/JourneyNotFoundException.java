package com.hdfc.log.customerjourney.exception;

public class JourneyNotFoundException extends RuntimeException {
    public JourneyNotFoundException(String message) {
        super(message);
    }
}
