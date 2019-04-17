package com.mercan.eagle.web.exception;

public class LicenseNotFoundException extends Exception {
    private String message;


    public LicenseNotFoundException() {
    }

    public LicenseNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
