package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.domain.Client;
import com.acme.banking.dbo.domain.domain.SavingAccount;

class ClientAccountTestHelper {

    public static final int DUMMY_ID = 1;
    public static final String DUMMY_NAME = "Dummy name";
    public static final double DUMMY_AMOUNT = 0D;


    public static SavingAccount createAccountForClient(Client client) {
        return new SavingAccount(DUMMY_ID, client, DUMMY_AMOUNT);
    }

    public static Client createClient() {
        return new Client(DUMMY_ID, DUMMY_NAME);
    }
}
