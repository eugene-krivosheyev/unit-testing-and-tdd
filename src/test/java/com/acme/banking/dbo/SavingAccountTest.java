package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTest {
    public static final int ID_STUB = 0;

    @Test
    public void shouldCreatedAccountWhenCreated() {
        Client fixture = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB, fixture, 0);

        assertAll("SavingAccount its properties",
                () -> Assert.assertTrue(sut.getId() != 0),
                () -> Assert.assertTrue(sut.getClient() != null),
                () -> Assert.assertTrue(sut.getAmount() >= 0));
    }


//    @Test
//    public void shouldGetErrorWhenIncorrectProperties() {
//        Client fixture = new Client(0, null);
//        SavingAccount sut = new SavingAccount(0, fixture, 0);
//
//        assertAll("Client store its properties",
//                () -> Assert.assertTrue(sut.getId() == 0)
////                () -> Assert.assertFalse(fixture.getName==null)
//        );
//
////        assertThat(sut,
////                allOf(
////                        hasProperty("id",
////                        hasProperty("client", notNullValue()),
//////                        hasProperty("id", equalTo(clientId)),
//////                        hasProperty("name", is(clientName))
////                ));
//    }
}
