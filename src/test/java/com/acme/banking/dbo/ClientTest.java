package com.acme.banking.dbo;



import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
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
    UUID stubClientId;
    UUID stubOtherClientId;
    Client sut;
    UUID stubAccountId;

    @Before
    public void setUp(){
        stubClientId = UUID.randomUUID();
        stubOtherClientId = UUID.randomUUID();
        stubAccountId = UUID.randomUUID();
    }


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region when
            sut = new Client(stubClientId, "dummy client name");
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubClientId),
                        notNullValue()
                ));
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        //region given
        String name = null;
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name is empty or null");
        //endregion

        //region when
        new Client(stubClientId, name);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //region given
        stubClientId = null;
        String name = "dummy client name";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Id is null");
        //endregion

        //region when
        new Client(stubClientId, name);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        //region given
        String name = "";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name is empty or null");
        //endregion

        //region when
        sut = new Client(stubClientId, name);
        //endregion
    }

    @Test
    public void shouldHaveSameClientIdsWhenAddSavingAccountToClient() {
        //region given
        sut = new Client(stubClientId, "dummy client name");
        //endregion

        //region when
        SavingAccount stubAccount = new SavingAccount(stubAccountId, sut, 1);
        //endregion

        assertEquals(stubClientId, stubAccount.getClientId());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddSavingAccountToClient() {
        //region given
        sut = new Client(stubClientId, "dummy client name");
        Client stubOtherClient = new Client(UUID.randomUUID(), "another dummy client name");

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");
        //endregion

        //region when
        SavingAccount stubAccount = new SavingAccount(stubAccountId, stubOtherClient, 1);
        sut.addIdToClientAccountIds(stubAccount);
        //endregion
    }

    @Test
    public void shouldHaveSameClientIdsWhenAddSavingAccountToClient1() {
        //region given
        sut = new Client(stubClientId, "dummy client name");
        //endregion

        //region when
        SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getClientId()).thenReturn(stubClientId);
        //endregion

        assertEquals(sut.getId(), stubAccount.getClientId());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSavingAccountClientIdNotEqualClientId() {
        //region given

        sut = new Client(stubClientId, "dummy client name");
        SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getClientId()).thenReturn(stubOtherClientId);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");
        //endregion

        //region when
        sut.addIdToClientAccountIds(stubAccount);
        //endregion
    }
}
