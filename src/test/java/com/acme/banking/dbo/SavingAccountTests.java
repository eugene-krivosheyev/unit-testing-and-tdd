package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
@RunWith(MockitoJUnitRunner.class)
public class SavingAccountTests {
    private final UUID DUMMY_ID = UUID.randomUUID();
    private final double DUMMY_AMOUNT = 0.;
    private final double DOUBLE_DELTA = 0.00001;
    private final double NEGATIVE_AMOUNT = -1;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    Client mockClient;

    @Test
    public void shouldCreateSavingAccountWhenValidArguments() {
        SavingAccount sut = new SavingAccount(DUMMY_ID, mockClient, DUMMY_AMOUNT);

        assertThat(
                sut,
                allOf(
                        hasProperty("id", equalTo(DUMMY_ID)),
                        hasProperty("client", equalTo(mockClient)),
                        hasProperty("amount", closeTo(DUMMY_AMOUNT, DOUBLE_DELTA))
                )
        );
    }

    @Test
    public void shouldNotCreateAccountWhenIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ID is null");

        AccountBuilder.builder()
                .withClient(mockClient)
                .withId(null)
                .build();
    }

    @Test
    public void shouldNotCreateAccountWhenClientIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Client is null");

        AccountBuilder.builder()
                .withClient(mockClient)
                .withClient(null)
                .build();
    }

    @Test
    public void shouldNotCreateAccountWhenAmountIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Amount is negative");

        AccountBuilder.builder()
                .withClient(mockClient)
                .withAmount(NEGATIVE_AMOUNT)
                .build();
    }
}
