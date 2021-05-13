package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;



@DisplayName("Test suite")
public class SavingAccountTest {
    @Test
    public void shouldFaildWhenNegativeId() {
	int illegalId = -1;
	double dummyAmount = 10.0;

	Client dummyClient = new Client(1, "legal name");

	assertThrows(IllegalArgumentException.class, () -> new SavingAccount(illegalId, dummyClient, dummyAmount));
    }

    @Test
    public void shouldFaildWhenNullClient() {
	int dummyAccountId = 1;
	double dummyAmount = 10.0;

	Client illegalClient = null;

	assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyAccountId, illegalClient, dummyAmount));
    }

    @Test
    public void shouldFaildWhenNegativeAmount() {
	int dummyAccountId = 10;
	double illegalAmount = -1;

	Client dummyClient = new Client(1, "legal client name");

	assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyAccountId, dummyClient, illegalAmount));
    }
}
