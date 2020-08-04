package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;



import static java.lang.StrictMath.random;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ClientTest {
    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        Integer stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion


        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));

        assertThat(sut.getName(),
                allOf(
                        notNullValue(),
                        equalTo(stubName)
                ));
        //endregion
    }

    @Test
    public void shouldNotNegativeWhenCreated() {
        //region given
        Integer stubId = -1;
        //endregion

        //region when
        try {
            Client sut = new Client(stubId, "dummy client name");
        }
        catch (IllegalArgumentException e)
        {
//            assertFalse(sut.isEmpty());
        }
        //endregion

        //region Assert | Then
  //      assertEquals("Not so good assert: got assumes", null, sut);
//        assertTrue(sut.contains(dummy));
//        assertFalse(sut.isEmpty());
        //endregion
    }

}
