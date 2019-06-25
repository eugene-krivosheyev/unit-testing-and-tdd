package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ClientTest {

    @Rule
    public final ExpectedException thrownRule = ExpectedException.none();

    @Rule
    public final TestName nameRule = new TestName();

    final String ANY_STRING = "ANY";
    final double ANY_DOUBLE = 1;

    @Test
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullId() {
        thrownRule.expect(IllegalArgumentException.class);
        new Client(null, ANY_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgExceptionWhenClientCreatedWithNullName() {
        UUID anyId = UUID.randomUUID();

        new Client(anyId, null);
    }

    @Test
    public void shouldReturnNotNullNameWhenClientCreatedWithSomeName() {
        String anyName = ANY_STRING;

        assertEquals(new Client(UUID.randomUUID(), anyName).getName(), anyName);
    }

    @Test
    public void shouldReturnAccountIdWhenClientAddAccountWasCalled() {
        Client clientTest = new Client(UUID.randomUUID(), ANY_STRING);
        SavingAccount dummyAccount = new SavingAccount(UUID.randomUUID(), clientTest, ANY_DOUBLE);

        clientTest.addAccount(dummyAccount);

        assertTrue(clientTest.getAccountIds().contains(dummyAccount.getId()));

        assertEquals("shouldReturnAccountIdWhenClientAddAccountWasCalled", nameRule.getMethodName());
    }

    @Test
    public void shouldThrowIlleagalStateExceptionWhenAddAccountToAnotherClient() {
        thrownRule.expect(IllegalStateException.class);
        thrownRule.expectMessage("Account does not belong to this client");
        Client clientTest = new Client(UUID.randomUUID(), ANY_STRING);
        Client anotherClient = new Client(UUID.randomUUID(), ANY_STRING);

        clientTest.addAccount(new SavingAccount(UUID.randomUUID(), anotherClient, ANY_DOUBLE));
    }


    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client nameRule");
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));
        //endregion
    }
}
