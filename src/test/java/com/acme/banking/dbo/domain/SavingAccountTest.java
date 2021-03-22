package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    public static final Client DUMMY_CLIENT = new Client(1, "dummy name");

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        SavingAccount sut = new SavingAccount(1, DUMMY_CLIENT, 1.0);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(1)),
                        hasProperty("client", is(DUMMY_CLIENT)),
                        hasProperty("amount", is(1.0))
                ));
        //endregion
    }


    @Test
    public void shouldThrowExceptionWhenIdIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, DUMMY_CLIENT, 1.0));
    }

    @Test
    public void shouldThrowExceptionWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, 1.0));
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1, DUMMY_CLIENT, -1.0));
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1, DUMMY_CLIENT, 0.0));
    }
}