package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ClientTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    @Rule
    public final ErrorCollector collector = new ErrorCollector();


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

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingAndIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id is null");
        Client sut = new Client(null, "dummy client name");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingAndNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name is null");
        UUID stubId = UUID.randomUUID();
        Client sut = new Client(stubId, null);

    }

    @Test
    public void shouldThrowExceptionWhenCreatingAndNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name is empty");
        UUID stubId = UUID.randomUUID();

        Client sut = new Client(stubId, "");
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

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddAlienAccount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("This account does not belong to the client");
        UUID stubId = UUID.randomUUID();
        Client stubClient = new Client(stubId, "dummy client name");
        Account stubAlienAccount = new SavingAccount(stubId, stubClient, 0);

        Client sut = new Client(UUID.randomUUID(), "sut");
        sut.addAccount(stubAlienAccount);
    }

    @Test
    public void shouldSaveIdWhenCreated() {
        UUID stubId = UUID.randomUUID();
        Client sut = new Client(stubId, "dummy client name");
        assertSame(stubId, sut.getId());
    }

    @Test
    public void shouldSaveNameWhenCreated() {
        UUID stubId = UUID.randomUUID();
        String name = "dummy client name";
        Client sut = new Client(stubId, name);
        assertSame(name, sut.getName());
    }

}
