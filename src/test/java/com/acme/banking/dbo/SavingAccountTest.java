package com.acme.banking.dbo;

import java.util.stream.Stream;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SavingAccountTest {

    private static final Client VALID_CLIENT = new Client(1, "test");

    @ParameterizedTest
    @MethodSource("validArgumentsForIdAndAmountAndClient")
    public void shouldStorePropertiesWhenIdIsPositiveAndClientIsNonNullAndAmountIsPositive(int id, double amount,
                                                                                           Client client) {
        //given

        //when
        final SavingAccount sut = new SavingAccount(id, client, amount);

        //then
        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }

    static Stream<Arguments> validArgumentsForIdAndAmountAndClient() {
        return Stream.of(
                arguments(1, 1.00, VALID_CLIENT),
                arguments(0, 1.00, VALID_CLIENT),
                arguments(1, 0.00, VALID_CLIENT)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        //given
        final int id = - 1;
        final double amount = 1.00;

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, VALID_CLIENT, amount))
                .withMessage("id should not be less than 0");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //given
        final int id = 1;
        final double amount = 1.00;

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, null, amount))
                .withMessage("client should not be null");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNegative() {
        //given
        final int id = 1;
        final double amount = - 1.00;

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, VALID_CLIENT, amount))
                .withMessage("amount should not be less than 0");
    }
}
