package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Nested
    class Positive {

        private Client client = null;

        @BeforeEach
        public void setUp() {
            client = new Client(0, "test");
        }

        @Test()
        public void shouldCreateWhenIdIsZero() {
            client = new Client(0, "test");
            SavingAccount sut = new SavingAccount(0, client, 10);

            Assertions.assertEquals(0, sut.getId());
            Assertions.assertEquals(client, sut.getClient());
            Assertions.assertEquals(10, sut.getAmount());
        }

        @Test()
        public void shouldCreateWhenAmountIsZero() {
            client = new Client(0, "test");
            SavingAccount sut = new SavingAccount(0, client, 0);

            Assertions.assertEquals(0, sut.getId());
            Assertions.assertEquals(client, sut.getClient());
            Assertions.assertEquals(0, sut.getAmount());
        }

        @Test
        public void shouldCreateWhenIdIsPositive() {
            client = new Client(0, "test");
            SavingAccount sut = new SavingAccount(10, client, 10);

            Assertions.assertEquals(10, sut.getId());
            Assertions.assertEquals(client, sut.getClient());
            Assertions.assertEquals(10, sut.getAmount());
        }

        @Test()
        public void shouldNotThrowWhenCorrectInput() {
            client = new Client(0, "test");

            Assertions.assertDoesNotThrow(() -> new SavingAccount(0, client, 12));
        }
    }

    @Nested
    class Negative {
        private Client client = null;

        @BeforeEach
        public void setUp() {
            client = new Client(0, "test");
        }

        @Test()
        public void shouldFailWhenClientIsNull() {
            client = null;
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(0, null, 12));
        }

        @Test()
        public void shouldFailWhenAmountIsNegative() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(0, client, -1));
        }

        @Test()
        public void shouldFailWhenIdIsNegative() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(-1, client, 10));
        }
    }
}
