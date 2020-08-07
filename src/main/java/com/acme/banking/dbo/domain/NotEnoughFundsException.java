package com.acme.banking.dbo.domain;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException() {
        super();
    }

    public NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughFundsException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughFundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
