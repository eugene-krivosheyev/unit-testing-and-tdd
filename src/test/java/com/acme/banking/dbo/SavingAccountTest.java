package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given

        Integer stubId =  100;
        double stubDouble = 123.3;
        Client sutClient = new Client(1, "dummy client name");
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);
        //endregion

        //region then
        assertEquals(stubId, sutSaving.getId());
        assertEquals(stubDouble, sutSaving.getAmount());
        assertEquals(sutClient, sutSaving.getClient());
        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull(){
        //region given

        Integer stubId =  null;
        double stubDouble = 123.3;
        Client sutClient = new Client(1, "dummy client name");
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);

    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenAmountLessThanZero(){
        //region given

        Integer stubId =  100;
        double stubDouble = -1;
        Client sutClient = new Client(1, "dummy client name");
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);

    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenClientIsNull(){
        //region given

        Integer stubId =  100;
        double stubDouble = 1.123;
        Client sutClient = null;
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);

    }




}
