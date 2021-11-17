package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class SavingAccountTest {

    @Test
    public void shouldThrowWhenNameIsNull() {
        // region 1: Given
        final int id = 0;
        final double amount = 0.0;
        final Client client = null;
        // end region

        // region 2: When

        // end region

        // region 3: Then
        assertThrows(IllegalArgumentException.class,  ()->new SavingAccount(id, client, amount) );
        // end region
    }

    @Test
    public void shouldNotThrowWhenNameValid() {
        // region 1: Given
        final int id = 0;
        final double amount = 0.0;
        final Client client = new Client(id, "Name");
        // end region

        // region 2: When

        // end region

        // region 3: Then
        assertDoesNotThrow(()->new SavingAccount(id, client, amount));
        // end region
    }
}
