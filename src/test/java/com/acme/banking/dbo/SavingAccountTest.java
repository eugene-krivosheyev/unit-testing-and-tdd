package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class SavingAccountTest {
    @Test
    public void shouldStoreArtifactsWhenCreated() {

        final int accountId = 1;
        final double accountAmount = 2;
        final Client dummy = new Client(3, "TestClient");

        SavingAccount sut = new SavingAccount(1, dummy, 2);
        assumeTrue(sut != null);
 /*
        Assertions.assertAll("Saving Acount should store its properties",
                ()-> assertEquals(accountId, sut.getId()),
                ()-> assertEquals(accountAmount, sut.getAmount()),
                ()-> assertEquals(dummy, sut.getClient())
        );
 */
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id",accountId)
                .hasFieldOrPropertyWithValue("amount", accountAmount)
                .hasFieldOrPropertyWithValue("client", dummy)
        ;
    }
/*
    @Test
    public void shouldNotCreatedWhenNegativeAccountId() {
        final int negativeAccountId = -1;
        final double dummyAccountAmount = 1;
        final Client dummy = new Client (1, "dummy name");

        final IllegalArgumentException thrown = assertThrows
                (IllegalArgumentException.class,
                        () -> new SavingAccount(negativeAccountId, dummy, dummyAccountAmount),
                        "id < 0: " + negativeAccountId);
    }

    @Test
    public void shouldNotCreatedWhenNegativeAccountAmount() {
        final int dummyAccountId = 1;
        final double negativeAccountAmount = -1;
        final Client dummy = new Client (1, "dummy name");

        //      example with correct 2nd check
        final IllegalArgumentException thrown1 = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, dummy, negativeAccountAmount));
        assertEquals("amount < 0: " + negativeAccountAmount, thrown1.getMessage());

        final IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, dummy, negativeAccountAmount),
                "amount << 0: " + negativeAccountAmount);
    }

    @Test
    public void shouldNotCreatedWhenClientIsNull() {
        final int dummyAccountId = 1;
        final double dummyAccountAmount = 1;
        final Client nullName = null;

        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, nullName, dummyAccountAmount),
                "client is null");
    }
*/
    @ParameterizedTest
    @MethodSource("paramsProvider")
    public void shouldNotCreatedWhenNegativeAccountIdOrNegativeAmountIdOrClientIsNull(ComplexTestParams complexTestParams) {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()-> new SavingAccount(complexTestParams.getAccountId(), complexTestParams.getClient(), complexTestParams.getAccountAmount()));
        assertEquals(complexTestParams.getMessageException(), thrown.getMessage());
    }

    static Stream<ComplexTestParams> paramsProvider () {
        return Stream.of(
                new ComplexTestParams(-1,1,new Client(1,"dummy"), "id < 0"),
                new ComplexTestParams(1,-1,new Client(1,"dummy"), "amount < 0"),
                new ComplexTestParams(1,1,null,"client is null")
        );
    }
}

class ComplexTestParams {
    private int accountId;
    private double accountAmount;
    private String messageException;
    private Client client;

    public ComplexTestParams (int accountId, double accountAmount, Client client, String messageException) {
        this.accountId = accountId;
        this.accountAmount = accountAmount;
        this.messageException = messageException;
        this.client = client;
    }

    public int getAccountId () {
        return this.accountId;
    }
    public double getAccountAmount() {
        return this.accountAmount;
    }
    public String getMessageException() {
        return this.messageException;
    }
    public Client getClient() {
        return this.client;
    }
}


