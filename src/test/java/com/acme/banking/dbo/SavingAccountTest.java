package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.apache.commons.lang.math.RandomUtils.nextLong;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {

    private long stubClId = nextLong();
    private Client client = new Client((long) stubClId, "SomeName");

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenCreatedWithNullId() {
        SavingAccount sut = new SavingAccount(null, client, (double) 43435678);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenCreatedWithNullAmount() {
        SavingAccount sut = new SavingAccount((long) 456787543, client, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenCreatedWithNegativeId() {
        SavingAccount sut = new SavingAccount((long) -12245, client, (double) 43435678);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenCreatedWithNegativeAmount() {
        SavingAccount sut = new SavingAccount((long) 12245, client, (double) -43435678);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetErrorWhenCreatedWithNullClient() {
        SavingAccount sut = new SavingAccount(3234l, null, 3435678d);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        long stubId = nextLong();
        double stubAmount = 5344567;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(stubId, client, stubAmount);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                )
        );

        assertThat(sut.getAmount(),
                allOf(
                        equalTo(stubAmount),
                        notNullValue()
                )
        );

        assertThat(sut.getClient(),
                allOf(
                        equalTo(client),
                        notNullValue()
                )
        );

        assertThat(sut.getClientId(),
                allOf(
                        equalTo(stubClId),
                        notNullValue()
                )
        );

        //endregion
    }

}
