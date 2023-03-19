package com.acme.banking.dbo.domain.exception;

public class NotEnoughAccountAmountException extends IllegalStateException {

    public NotEnoughAccountAmountException(int accountId, double currentAmount, double withdrawAmount) {
        super(String.format("Account with id %d does not have enough money to withdraw %f, current balance is %f"
                , accountId, withdrawAmount, currentAmount));
    }
}
