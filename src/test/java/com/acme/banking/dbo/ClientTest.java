package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    @Mock
    private UUID repo;

    @Test(timeout = 10_000)
    public void shouldSavePropertiesWhenCreated() {
        //region given
        UUID stubId = UUID.randomUUID();
        //endregion

        //region when
        if(1==1) throw new RuntimeException();
        Client sut = new Client(stubId, "dummy client name");
        //endregion

        //region then
        assertFalse(true);
        assertThat(sut.getId(),
            allOf(
                equalTo(stubId),
                notNullValue()
        ));
        //endregion
    }

    //@Ignore
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        new Client(null, "dummy client name");
    }
}
