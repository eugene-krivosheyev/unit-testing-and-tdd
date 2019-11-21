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
import static org.mockito.Mockito.*;

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
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");

        UUID stubId = UUID.randomUUID();
        String name = null;
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
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");

        UUID dummyId = null;
        String name = "dummy client name";
        //endregion

        //region when
        new Client(dummyId, name);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");

        UUID stubId = UUID.randomUUID();
        String emptyName = "";
        //endregion

        //region when
        new Client(stubId, emptyName);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldHaveSameClientIdsWhenAddSavingAccountToClient() {

        //region given
        UUID stubClientId = UUID.randomUUID();
        UUID stubAccountId = UUID.randomUUID();
        SavingAccount stubAccount = mock(SavingAccount.class);
        Client sut = new Client(stubClientId, "dummy client name");
        when(stubAccount.getClient()).thenReturn(sut);
        //endregion

        //region when
        sut.addIdToClientAccountIds(stubAccount);
        //endregion

        //region then
        assertTrue(sut.getAccounts().contains(stubAccount));
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddSavingAccountToWrongClient() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");

        UUID stubClientId = UUID.randomUUID();
        UUID stubOtherClientId = UUID.randomUUID();
        Client stubWrongClient = mock(Client.class);
        when(stubWrongClient.getId()).thenReturn(stubOtherClientId);

        final SavingAccount stubSavingAccount = mock(SavingAccount.class);
        when(stubSavingAccount.getClient()).thenReturn(stubWrongClient);
        //endregion

        //region when
        Client sut = new Client(stubClientId, "dummy name");
        sut.addIdToClientAccountIds(stubSavingAccount);
        //endregion

        //region then
        //endregion
    }

}
