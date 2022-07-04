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
        int id = 1;
        String name = "testName";
        //endregion

        //region when
        Client sut = new Client(id, name);
        //endregion

        //region then
        assertEquals(id, sut.getId());
        assertEquals(name, sut.getName());
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenIdAreNegative() {
        //region given
        int id = -1;
        String name = "testName";
        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new Client(id, name));
        //endregion

        //region then
        //endregion
    }

    @Test
    public void shouldNotCreateClientWhenNameIsNull() {
        //region given
        int id = 1;
        String name = null;
        //endregion

        //region when
        assertThrows(IllegalArgumentException.class, () -> new Client(id, name));
        //endregion

        //region then
        //endregion
    }

}