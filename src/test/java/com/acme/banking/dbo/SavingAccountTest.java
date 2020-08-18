package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
//import static com.sun.tools.doclint.Entity.times;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Integer accId = 1;
        Integer clientId=1;
        Client accClient = mock(Client.class);
        double accAmount= 1.0;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion

        //region then
        assertThat("Свойства объекта должны сохраняться",sut,
                allOf(
                        hasProperty("id", is(accId)),
                        hasProperty("client", is(accClient)),
                        hasProperty("amount", is(accAmount))
                )
        );
       //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        //region given
        Integer accId = null;
        Client accClient = new Client(1,"dummy client name");
        double accAmount= 0.12;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNegative() {
        //region given
        Integer accId = -1;
        Client accClient = new Client(1,"dummy client name");
        double accAmount= 0.12;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenClientIsNull() {
        //region given
        Integer accId = 1;
        Client accClient =  null;
        double accAmount= 0.12;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenAmountIsNegative() {
        //region given
        Integer accId = 1;
        Client accClient = new Client(1,"dummy client name");
        double accAmount= - 0.12;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion
    }
}
