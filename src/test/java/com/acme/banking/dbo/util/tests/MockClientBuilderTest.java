package com.acme.banking.dbo.util.tests;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.util.MockClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MockClientBuilderTest {

    private MockClientBuilder clientBuilder;

    @BeforeEach
    void setUp() {
        clientBuilder = new MockClientBuilder();
    }

    @Test
    void shouldSetClientId() {
        int id = 1;
        Client mock = new MockClientBuilder()
                .withId(id)
                .build();

        assertEquals(id, mock.getId());
    }

    @Test
    void shouldSetAccounts() {
        Set<Account> accounts = Set.of(mock(Account.class));
        Client mock = new MockClientBuilder()
                .withAccounts(accounts)
                .build();

        assertEquals(accounts, mock.getAccounts());
    }

    @Test
    void shouldSetName() {
        String name = "Dummy";
        Client mock = new MockClientBuilder()
                .withName(name)
                .build();

        assertEquals(name, mock.getName());
    }
}