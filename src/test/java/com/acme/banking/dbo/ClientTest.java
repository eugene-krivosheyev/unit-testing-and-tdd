package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClientTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSavePropertiesWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        not(0)
                ));

        assertThat(sut.getName(),
                allOf(
                        equalTo(stubName),
                        notNullValue(),
                        not("")
                ));
        //java-hamcrest
        assertThat(sut, allOf(
                hasProperty("id", is(stubId)),
                hasProperty("name", is(stubName))
        ));
        //endregion
    }

    @Test
    public void shouldGetIdWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat("Get invalid id when client created", stubId, equalTo(sut.getId()));
        //endregion
    }

    @Test
    public void shouldGetNameWhenCreated() {
        //region given
        int stubId = 1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion

        //region then
        assertThat("Get invalid name when client created", stubName, equalTo(sut.getName()));
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdEqualsZero() {
        exception.expect(IllegalArgumentException.class);

        //region given
        int stubId = 0;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdLessZero() {
        exception.expect(IllegalArgumentException.class);

        //region given
        int stubId = -1;
        String stubName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        exception.expect(IllegalArgumentException.class);

        //region given
        int stubId = 1;
        String stubName = null;
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        exception.expect(IllegalArgumentException.class);

        //region given
        int stubId = 1;
        String stubName = "";
        //endregion

        //region when
        Client sut = new Client(stubId, stubName);
        //endregion
    }
}
