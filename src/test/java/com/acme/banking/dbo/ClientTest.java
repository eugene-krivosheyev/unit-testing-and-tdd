package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ClientTest {
    @Rule
    public final TestRule globalTimeout = Timeout.seconds(5);

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

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

        assertThat(sut.getName(),
                allOf(
                        equalTo("dummy client name"),
                        notNullValue()
                ));
        assertEquals(false, sut.getName().equals(""));
        //endregion
    }

    @Test
    public void shouldThrowExeptionWhenCreatedWithWrongName(){
        thrown.expect(IllegalArgumentException.class);

        String name = "";
        UUID someID = UUID.randomUUID();

        Client sut = new Client(someID, name);

    }

    @Test
    public void shouldSizeIncrementedAndElementContainedWhenElementAddedInAccountsCollection(){
        Client client = new Client(UUID.randomUUID(), "Some random name");
        assertEquals(client.getAccounts().size(), 0);

        client.addAccount(new SavingAccount(UUID.randomUUID(), client, 1.0d));
        assertEquals(client.getAccounts().size(), 1);
    }

    @Test
    public void shouldReturnSameNameOfAccountOwnerWhenAddingAccount(){
        Client sut = new Client (UUID.randomUUID(), "Owner");
        SavingAccount withAnotherOwner = new SavingAccount(UUID.randomUUID(),
                new Client(UUID.randomUUID(), "Some different guy "), 100.0d);

        sut.addAccount(withAnotherOwner);

        assertTrue(sut.getAccounts().iterator().next().getClientName().equals("Owner"));
        assertEquals(sut.getAccounts().iterator().next().getClientId(), sut.getId());
    }
}
