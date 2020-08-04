package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    @Test
    public void shouldSaveAmountWhenCreated() {
        //region given

        int stubId =  (int)Math.random();
        double stubDouble = Math.random();
        //endregion

        //region when
        Client sutClient = new Client(stubId, "dummy client name");
        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);
        //endregion

        //region then
        assertThat(sutSaving.getAmount(),
                allOf(
                        equalTo(stubDouble),
                        notNullValue()
                ));
        //endregion
    }


}
