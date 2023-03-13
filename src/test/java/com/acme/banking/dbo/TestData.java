package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;

public class TestData {
    public static final String TEST_CLIENT_NAME = "John";
    public static final int TEST_CLIENT_ID = 0;

    public static final int TEST_ACCOUNT_ID = 0;

    public static final double TEST_AMOUNT = 1.0;

    public static Client stubClient() {
        return new Client(TEST_CLIENT_ID, TEST_CLIENT_NAME);
    }
}
