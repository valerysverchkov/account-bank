package ru.iteco.accountbank.model.exception;

public class BankBookWithCurrencyAlreadyHaveException extends RuntimeException {
    public BankBookWithCurrencyAlreadyHaveException() {
    }

    public BankBookWithCurrencyAlreadyHaveException(String message) {
        super(message);
    }

    public BankBookWithCurrencyAlreadyHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookWithCurrencyAlreadyHaveException(Throwable cause) {
        super(cause);
    }

    public BankBookWithCurrencyAlreadyHaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
