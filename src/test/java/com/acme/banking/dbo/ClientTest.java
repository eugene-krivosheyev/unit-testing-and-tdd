package com.acme.banking.dbo;



import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        //region given
        UUID stubId = UUID.randomUUID();
        String name = null;
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");
        //endregion

        //region when
        new Client(stubId, name);
        //endregion

        //region then

        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //region given
        UUID stubId = null;
        String name = "dummy client name";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");
        //endregion

        //region when
        new Client(stubId, name);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        //region given
        UUID stubId = UUID.randomUUID();

        String name = "";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");
        //endregion

        //region when
        new Client(stubId, name);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldHaveSameClientIdsWhenAddSavingAccountToClient() {
        //region given
        UUID stubClientId = UUID.randomUUID();
        Client stubClient = new Client(stubClientId, "dummy client name");
        UUID stubAccountId = UUID.randomUUID();
        //endregion

        //region when
        SavingAccount sub = new SavingAccount(stubAccountId, stubClient, 1);

        //endregion

        //region then
        assertEquals(stubClientId, sub.getClientId());

//        assertThat(sut.getId(),
//                allOf(
//                        equalTo(stubClientId),
//                        notNullValue()
//                ));
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddSavingAccountToClient() {
        //region given
        UUID stubClientId = UUID.randomUUID();
        Client stubClient = new Client(stubClientId, "dummy client name");
        Client stubWrongClient = new Client(UUID.randomUUID(), "another dummy client name");
        UUID stubAccountId = UUID.randomUUID();

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");
        //endregion

        //region when
        SavingAccount sub = new SavingAccount(stubAccountId, stubWrongClient, 1);
        stubClient.addIdToClientAccountIds(sub);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddSavingAccountToClientUnit() {
        //region given
        UUID stubClientId = UUID.randomUUID();
        UUID stubWrongClientId = UUID.randomUUID();
        Client stubClient = new Client(stubClientId, "dummy client name");

        SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getClient().getId()).thenReturn(stubWrongClientId);

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");
        //endregion

        //region when
        stubClient.addIdToClientAccountIds(stubAccount);
        //endregion
    }

}
