package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest extends AbstractTest {
    @ParameterizedTest
    @MethodSource("guardClausesParams")
    public void shouldNotBeCreatedWhenNegativeId(SavingAccountTestParams params) {
        // region Given
        final Executable producer = () -> new SavingAccount(params.getId(), params.getClient(), params.getAmount());
        // endregion

        assertThrowIllegalArgumentExceptionWithMessageWhenProducedWithIncorrectParameters(producer, params.getExceptionMessage());
    }

    private static Stream<SavingAccountTestParams> guardClausesParams() {
        return Stream.of(
                new SavingAccountTestParams(-1, -1.0, null, "ID is expected to be positive int"),
                new SavingAccountTestParams(1, -1.0, null, "Amount is expected to be positive double"),
                new SavingAccountTestParams(1, 1.0, null, "Client is NULL")
        );
    }

    @Test
    public void getFieldsWhenCreatedCorrectlyAndRequested() {
        // region Given
        final int expectedId = 1;
        final double expectedAmount = 1.0;
        final Client expectedClient = new Client(1, "Test Name");
        final Account account = new SavingAccount(expectedId, expectedClient, expectedAmount);
        // endregion

        // region When
        final double amount = account.getAmount();
        final Client actualClient = account.getClient();
        final int id = account.getId();
        // endregion

        // region Then
        assertAll(
                () -> assertEquals(expectedId, id, "Getter for 'id' seems isn't working"),
                () -> assertSame(expectedClient, actualClient, "Getter for 'client' seems isn't working"),
                () -> assertEquals(expectedAmount, amount, "Getter for 'amount' seems isn't working")
        );

        MatcherAssert.assertThat(account,
                Matchers.allOf(
                        Matchers.hasProperty("id", equalTo(expectedId)),
                        Matchers.hasProperty("amount", equalTo(expectedAmount)),
                        Matchers.hasProperty("client", sameInstance(expectedClient))
                )
        );

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(id).isEqualTo(expectedId);
        softly.assertThat(amount).isEqualTo(expectedAmount);
        softly.assertThat(actualClient).isSameAs(expectedClient);
        softly.assertAll();
        // endregion
    }
}

class SavingAccountTestParams {
    private final int id;
    private final double amount;
    private final Client client;
    final private String exceptionMessage;

    public SavingAccountTestParams(
            final int id,
            final double amount,
            final Client client,
            final String exceptionMessage
    ) {
        this.id = id;
        this.amount = amount;
        this.client = client;
        this.exceptionMessage = exceptionMessage;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Client getClient() {
        return client;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}