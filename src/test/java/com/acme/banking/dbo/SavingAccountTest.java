package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    public static final UUID CLIENT_ID = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f111");
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    public static final String CLIENT_NAME = "dummy client name";
    public static final Client test_client = new Client(CLIENT_ID, CLIENT_NAME);
    public static final double TEST_AMOUNT = 123.4;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldStorePropertiesWhenCreated() {
        //region when
        SavingAccount sut = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        //endregion

        //region then
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(ID_STUB)),
                hasProperty("id", not(equalTo(test_client.getId()))),
                hasProperty("client", notNullValue()),
                hasProperty("client", equalTo(test_client)),
                hasProperty("amount", equalTo(TEST_AMOUNT))
        ));
        //endregion
    }

    @Test
    public void shouldExceptionWhenIdIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("id must not be null");
        new SavingAccount(null, test_client, TEST_AMOUNT);
    }

    @Test
    public void shouldExceptionWhenClientIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("client must not be null");
        new SavingAccount(ID_STUB, null, TEST_AMOUNT);
    }

    @Test
    public void shouldExceptionWhenClientIdEqualsId() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("id must not be equal client's id");
        new SavingAccount(test_client.getId(), test_client, TEST_AMOUNT);
    }

    @Test
    public void shouldExceptionWhenAmountIsNegative() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("amount must not be positive");
        new SavingAccount(ID_STUB, test_client, -3);
    }

    @Test
    public void shouldReturnId() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        UUID sut = account.getId();

        assertEquals(ID_STUB, sut);
    }

    @Test
    public void shouldReturnClient() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        Client sut = account.getClient();

        assertEquals(test_client, sut);
    }

    @Test
    public void shouldReturnAmount() {
        SavingAccount account = new SavingAccount(ID_STUB, test_client, TEST_AMOUNT);
        double sut = account.getAmount();

        assertEquals(TEST_AMOUNT, sut, 0.001);
    }
}
