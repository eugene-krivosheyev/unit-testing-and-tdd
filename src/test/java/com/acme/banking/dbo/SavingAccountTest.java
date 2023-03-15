package com.acme.banking.dbo;

import java.util.Random;
import java.util.stream.Stream;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SavingAccountTest {

    private static final Random RANDOM = new Random();
    private static final Client VALID_CLIENT = new Client(RANDOM.nextInt(2147483647), "test");

    @ParameterizedTest
    @MethodSource("validArgumentsForIdAndAmountAndClient")
    public void shouldStorePropertiesWhenIdIsPositiveAndClientIsNonNullAndAmountIsPositive(int id, double amount,
                                                                                           Client client) {
        //given

        //when
        SavingAccount sut = new SavingAccount(id, client, amount);

        //then
        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }

    static Stream<Arguments> validArgumentsForIdAndAmountAndClient() {
        return Stream.of(
                arguments(RANDOM.nextInt(2147483647), Math.random(), VALID_CLIENT),
                arguments(0, Math.random(), VALID_CLIENT),
                arguments(RANDOM.nextInt(2147483647), 0.0, VALID_CLIENT)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        //given
        int id = - (RANDOM.nextInt(2147483647));
        double amount = Math.random();

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, VALID_CLIENT, amount))
                .withMessage("id should not be less than 0");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //given
        int id = RANDOM.nextInt(2147483647);
        double amount = Math.random();

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, null, amount))
                .withMessage("client should not be null");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNagative() {
        //given
        int id = RANDOM.nextInt(2147483647);
        double amount = - (Math.random());

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SavingAccount(id, VALID_CLIENT, amount))
                .withMessage("amount should not be less than 0");
    }
}
