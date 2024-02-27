package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Nested
    class Positive {
        @Test()
        public void shouldCreateWhenIdIsZero() {
            Client client = new Client(0, "test");
            SavingAccount sut = new SavingAccount(0, client, 10);

            Assertions.assertEquals(0, sut.getId());
            Assertions.assertEquals(client, sut.getClient());
            Assertions.assertEquals(10, sut.getAmount());
        }

        @Test()
        public void shouldCreateWhenIdIsPositive() {
            Client client = new Client(0, "test");
            SavingAccount sut = new SavingAccount(10, client, 10);

            Assertions.assertEquals(0, sut.getId());
            Assertions.assertEquals(client, sut.getClient());
            Assertions.assertEquals(10, sut.getAmount());
        }

        @Test()
        public void shouldNotThrowWhenCorrectInput() {
            Client client = new Client(0, "test");

            Assertions.assertDoesNotThrow(() -> new SavingAccount(0, client, 12));
        }
    }

    @Nested
    class Negative {
        @Test()
        public void shouldFailWhenClientIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(0, null, 12));
        }

        @Test()
        public void shouldFailWhenAmountIsNegativeIsEmpty() {
            Client client = new Client(0, "test");

            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(0, client, -1));
        }

        @Test()
        public void shouldFailWhenIdIsNegative() {
            Client client = new Client(0, "test");

            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new SavingAccount(-1, client, 10));
        }
    }
}
