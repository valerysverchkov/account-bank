package ru.iteco.accountbank.model.exception;

public class CurrencyNotEqualsException extends RuntimeException {
    public CurrencyNotEqualsException() {
    }

    public CurrencyNotEqualsException(String message) {
        super(message);
    }

    public CurrencyNotEqualsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyNotEqualsException(Throwable cause) {
        super(cause);
    }

    public CurrencyNotEqualsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
