package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Smirnov Sergey (ruasisl)
 */
class ClientTest {
    private static final Integer VALID_ID = 1;
    private static final String VALID_NAME = "validName";

    @Test
    public void shouldCreateClientWhenParamsAreValid() {
        Client sut = new Client(VALID_ID, VALID_NAME);

        assertAll(
                () -> assertEquals(VALID_ID, sut.getId()),
                () -> assertEquals(VALID_NAME, sut.getName())
        );
    }

    @Test
    public void shouldNotCreateClientWhenIdAreNegative() {
        int invalidId = -1;
        String dummyName = "dummyName";

        assertThrows(IllegalArgumentException.class, () -> new Client(invalidId, dummyName));
    }

    @Test
    public void shouldNotCreateClientWhenNameIsNull() {
        String invalidName = null;

        assertThrows(IllegalArgumentException.class, () -> new Client(VALID_ID, invalidName));
    }

    @Test
    public void shouldReturnAccount(){
        Account account = Mockito.mock(Account.class);

        Client sut = new Client(VALID_ID, VALID_NAME);

        sut.addAccount(account);

        assertThat(sut.getAccounts()).containsExactlyInAnyOrder(account);
    }
}