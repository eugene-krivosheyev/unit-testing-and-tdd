package com.acme.banking.dbo;

import java.util.Random;
import java.util.stream.Stream;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@DisplayName("Test suite")
public class ClientTest {

    private static final Random RANDOM = new Random();

    @Test @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @ParameterizedTest
    @MethodSource("validArgumentsForClientIdAndName")
    public void shouldStoreIdAndNameWhenNonEmptyNameAndNonNegativeId(int id, String name){
        //given

        //when
        Client sut = new Client(id, name);

        //then
        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(name, sut.getName())
        );
    }

    static Stream<Arguments> validArgumentsForClientIdAndName() {
        return Stream.of(
                arguments(RANDOM.nextInt(2147483647), "test name"),
                arguments(0, "test name")
        );
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenIdIsNegative(){
        //given
        int id = - (RANDOM.nextInt(2147483647) + 1) ;
        String name = "test";

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Client(id, name))
                .withMessage("id should not be less than 0");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "", "  ", "\t", "\n" })
    public void shouldGetIllegalArgumentExceptionWhenNameIsEmptyOrNull(String name){
        //given
        int id = RANDOM.nextInt(2147483647);

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Client(id, name))
                .withMessage("name should not be null or empty");
    }

}
