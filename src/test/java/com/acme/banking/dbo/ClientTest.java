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
import static org.mockito.Mockito.*;

public class ClientTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private UUID stubId;
    private String dummy_client_name;

    @Before
    public void setUp() {
        stubId = UUID.randomUUID();
        dummy_client_name = "dummy client name";
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {

        //region given
        //endregion

        //region when
        Client sut = new Client(stubId, dummy_client_name);
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
        //endregion

        //region when
        new Client(stubId, null);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is null");
        //endregion

        //region when
        new Client(null, dummy_client_name);
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is null or empty");
        //endregion

        //region when
        new Client(stubId, "");
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldHaveSameClientIdsWhenAddSavingAccountToClient() {

        //region given
        SavingAccount stubAccount = mock(SavingAccount.class);
        Client sut = new Client(stubId, dummy_client_name);
        when(stubAccount.getClient()).thenReturn(sut);
        //endregion

        //region when
        sut.addIdToClientAccountIds(stubAccount);
        //endregion

        //region then
        org.fest.assertions.api.Assertions.assertThat(sut.getAccounts()).containsExactly(stubAccount);
        //endregion
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddSavingAccountToWrongClient() {
        //region given
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("cannot add account to client because it is wrong");

        Client stubOtherClient = new MockitoClientBuilder().build();

        final SavingAccount stubSavingAccount = mock(SavingAccount.class);
        when(stubSavingAccount.getClient()).thenReturn(stubOtherClient);
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy name");
        sut.addIdToClientAccountIds(stubSavingAccount);
        //endregion

        //region then
        //endregion
    }
}
