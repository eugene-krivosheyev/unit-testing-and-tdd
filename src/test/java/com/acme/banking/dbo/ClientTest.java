package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test-Case: class
 * SUT: SUTTest
 */
public class ClientTest {
    @Test //BDD = AT + DDD
    public void shouldSavePropertiesWhenCreated() {
        //region fixture | given | arrange
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when | act
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then | assert
        Assert.assertEquals(stubId, sut.getId());
        Assert.assertEquals(.3, .1 + .2, .01);
        //region later
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
        //endregion
    }
}
