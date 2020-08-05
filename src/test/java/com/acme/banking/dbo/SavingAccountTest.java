package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Integer accId = 1;
        Client accClient = new Client(1,"dummy client name");
        double accAmount= 0.12;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accId,accClient,accAmount);
        //endregion

        //region then
        assertEquals(accId,sut.getId());
        assertEquals(accClient,sut.getClient());
        assertEquals(java.util.Optional.of(accAmount),java.util.Optional.of(sut.getAmount()));
//        assertEquals(accAmount,sut.getAmount());
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
