package ru.iteco.accountbank.model.exception;

public class BankBookNumberCannotBeModifiedException extends RuntimeException {

    public BankBookNumberCannotBeModifiedException() {
    }

    public BankBookNumberCannotBeModifiedException(String message) {
        super(message);
    }

    public BankBookNumberCannotBeModifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookNumberCannotBeModifiedException(Throwable cause) {
        super(cause);
    }

    public BankBookNumberCannotBeModifiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
