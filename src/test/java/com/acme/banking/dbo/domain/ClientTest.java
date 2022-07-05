package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Smirnov Sergey (ruasisl)
 */
class ClientTest {
    @Test
    public void shouldCreateClientWhenParamsAreValid() {
        //region given
        int validId = 1;
        String validName = "validName";
        //endregion

        //region when
        Client sut = new Client(validId, validName);
        //endregion

        //region then
        assertAll(
                () -> assertEquals(validId, sut.getId()),
                () -> assertEquals(validName, sut.getName())
        );
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenIdAreNegative() {
        //region given
        int invalidId = -1;
        String dummyName = "dummyName";
        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new Client(invalidId, dummyName));
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNameIsNull() {
        //region given
        int validId = 1;
        String invalidName = null;
        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new Client(validId, invalidName));
        //endregion

        //region then
        //endregion
    }
}