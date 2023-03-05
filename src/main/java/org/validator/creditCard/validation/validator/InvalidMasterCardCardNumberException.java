package org.validator.creditCard.validation.validator;


// Define a custom exception class
public final class InvalidMasterCardCardNumberException extends Exception {
    // Add a serialVersionUID to custom exceptions to ensure versioning compatibility
    private static final long serialVersionUID = 1L;
    private final int errorCode;

    public InvalidMasterCardCardNumberException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}




