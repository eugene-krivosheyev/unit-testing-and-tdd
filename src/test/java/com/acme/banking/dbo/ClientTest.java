package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assume;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatedAndIdIsNull() {
        Client sut = new Client(null, "dummy client name");

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatedAndNameIsNull() {
        UUID stubId = UUID.randomUUID();

        Client sut = new Client(stubId, null);

    }

    @Test
    public void shouldSizeIncrementedAndAccountContainedWhenAccountAdded() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");
        Account stubAccount = new SavingAccount(stubId, stubClient, 0);

        Client sut = new Client(stubId, "sut");
        Assume.assumeTrue(sut.getAccounts().isEmpty());

        sut.addAccount(stubAccount);

        assertEquals(1, sut.getAccounts().size());
        assertTrue(sut.getAccounts().contains(stubAccount));

    }

    @Test
    public void shouldSizeDecrementedAndAccountNoContainedWhenAccountRemove() {
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");
        Account stubAccount = new SavingAccount(stubId, stubClient, 0);

        Client sut = new Client(stubId, "sut");
        sut.addAccount(stubAccount);
        Assume.assumeFalse(sut.getAccounts().isEmpty());

        sut.removeAccount(stubAccount);

        assertEquals(0, sut.getAccounts().size());
        assertFalse(sut.getAccounts().contains(stubAccount));

    }
}
