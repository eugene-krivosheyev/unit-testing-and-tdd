package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class SavingAccountTest {
    @Test(expected = IllegalArgumentException.class, timeout = 10_000)
    public void shouldNotCreateWhenIdIsNull() {
        Client dummy = mock(Client.class);
        SavingAccount sut = new SavingAccount(null, dummy, 0);
    }
}
