package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assume;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void shouldAddAccountToAccountsList(){
        //region given
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        Assume.assumeTrue(sut.getAccounts().isEmpty());

        sut.addAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 1);
        //endregion
    }

    @Test
    public void shouldRemoveAccountFromAccountList(){
        //region given
        UUID stubId = UUID.randomUUID();
        Client dummyClient = new Client(stubId, "dummy client name");
        Account account = new SavingAccount(stubId,dummyClient,0d);
        //endregion

        //region when
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        sut.addAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 1);
        //endregion

        sut.removeAccount(account);

        //region then
        assertTrue(sut.getAccounts().size() == 0);
        //endregion
    }

    @Test
    public void shouldThrowExceptionWhenNullUUID(){
        try {
            Client dummyClient = new Client(null, "dummy client name");
        } catch (IllegalArgumentException ex){
            assertTrue(ex.getMessage().equals("id is null"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenNullOrEmptyName(){
        UUID stubId = UUID.randomUUID();
        try {
            Client dummyClient = new Client(stubId, null);
        } catch (IllegalArgumentException ex){
            assertTrue(ex.getMessage().equals("name is null or empty"));
        }
        try {
            Client dummyClient = new Client(stubId, "");
        } catch (IllegalArgumentException ex){
            assertTrue(ex.getMessage().equals("name is null or empty"));
        }
    }
}
