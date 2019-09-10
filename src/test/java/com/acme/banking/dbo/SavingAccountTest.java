package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        UUID stubID = UUID.randomUUID();
        Client stubClient = new Client(stubID, "dummy name");
        SavingAccount sut = new SavingAccount(stubID, stubClient, 100d);

        assertEquals(sut.getAmount(), 100d, 0.001d);
        assertThat(sut.getId(), CoreMatchers.allOf(equalTo(stubID), notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfClientIsNull() {
        Client stubClient = null;
        UUID stubID = UUID.randomUUID();
        new SavingAccount(stubID, stubClient, 100d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfIdIsNull() {
        UUID stubID = null;
        Client stubClient = new Client(stubID, "dummy name");
        new SavingAccount(stubID, stubClient, 100d);
    }
}
