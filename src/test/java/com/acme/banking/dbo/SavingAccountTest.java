package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class SavingAccountTest {
    @Test
    //модульный юнит тест - конкретная проверка данного
    public void shouldSavePropertiesWhenCreated() {
        //region given

        Integer stubId =  1;
        double stubDouble = 1.0;
        final Client sutClient = mock(Client.class);
        //endregion

        //region when
        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);
        //endregion

        //region then
        assertEquals(stubId, sutSaving.getId());
        assertEquals(stubDouble, sutSaving.getAmount());
        assertEquals(sutClient, sutSaving.getClient());


        assertThat(sutSaving, allOf(
                                 hasProperty("name", is("dummy client name"))
                                ,hasProperty("amount", is(stubDouble))
                                ,hasProperty("client",is(sutClient))
                )
        );


        //endregion
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull(){
        //region given

        Integer stubId =  null;
        double stubDouble = 1.0;
        Client sutClient = mock(Client.class);
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);

    }

    //интеграционный когда есть другой класс
    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenAmountLessThanZero(){
        //region given

        Integer stubId =  1;
        double stubDouble = -1;
        Client dummyClient = mock(Client.class);
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, dummyClient, stubDouble);

    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenClientIsNull(){
        //region given

        Integer stubId =  1;
        double stubDouble = 1.0;
        Client sutClient = null;
        //endregion

        //region when

        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);

    }




}
