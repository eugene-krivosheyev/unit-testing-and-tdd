package com.acme.banking.dbo.domain;

public class Errors {
    public static final String CLIENT_NEGATIVE_ID_MESSAGE = "Client id cannot be negative";
    public static final String CLIENT_EMPTY_NAME_MESSAGE = "Client name cannot be null or empty";
    public static final String ACCOUNT_NEGATIVE_ID_MESSAGE = "SavingAccount id cannot be negative";
    public static final String ACCOUNT_NULL_CLIENT_MESSAGE = "SavingAccount client cannot be null";
    public static final String ACCOUNT_NEGATIVE_AMOUNT_MESSAGE = "SavingAccount amount cannot be negative";
    public static final String CLIENT_ACCOUNT_ADD_NULL = "Account cannot be null";
    public static final String CLIENT_ACCOUNT_WRONG_OWNER = "Account doesn't belong to client";
    public static final String CLIENT_ACCOUNT_DUPLICATE = "Duplicating account";
}
