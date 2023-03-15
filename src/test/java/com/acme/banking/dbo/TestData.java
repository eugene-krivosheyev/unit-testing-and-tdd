package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

public class TestData {
    public static final int VALID_ID_1 = 1;
    public static final int VALID_ID_0 = 0;
    public static final double VALID_AMOUNT_0 = 0.0;
    public static final double VALID_AMOUNT_1 = 1.0;
    public static final String VALID_CLIENT_NAME = "dummy_name";
    public static final Client CLIENT_SUT = new Client(VALID_ID_1, VALID_CLIENT_NAME);
    public static final SavingAccount SAVING_ACCOUNT_SUT =
            new SavingAccount(VALID_ID_1, CLIENT_SUT, VALID_AMOUNT_1);
}