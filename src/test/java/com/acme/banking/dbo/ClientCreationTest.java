package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ClientCreationTest {

    @Nested
    class Positive {
        @Test()
        public void shouldCreateWhenIdIsZero() {
            Client sut = new Client(0, "Petr");
            Assertions.assertEquals(0, sut.getId());
            Assertions.assertEquals("Petr", sut.getName());
        }

        @Test()
        public void shouldCreateWhenIdIsPositive() {
            Client sut = new Client(10, "Petr");
            Assertions.assertEquals(10, sut.getId());
            Assertions.assertEquals("Petr", sut.getName());
        }

        @Test()
        public void shouldNotThrowWhenCorrectInput() {
            Assertions.assertDoesNotThrow(() -> new Client(0, "Petr"));
        }
    }

    @Nested
    class Negative {
        @Test()
        public void shouldFailWhenNameIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(0, null));
        }

        @Test()
        public void shouldFailWhenNameIsEmpty() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(0, ""));
        }

        @Test()
        public void shouldFailWhenNameIsBlank() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(0, " "));
        }

        @Test()
        public void shouldFailWhenIdIsNegative() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(-5, "Petr"));
        }
    }
}