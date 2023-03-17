package com.acme.banking.dbo;

import java.util.stream.Stream;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
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
                arguments(1, "test name"),
                arguments(0, "test name")
        );
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenIdIsNegative(){
        //given
        final int id = - 1 ;
        final String name = "test";

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
        final int id = 1;

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Client(id, name))
                .withMessage("name should not be null or empty");
    }

    @Test
    public void shouldSaveAccountWhenTheAccountIsOwnedByCurrentClient(){
        //given
        final Client sut = new Client(1, "test");
        final SavingAccount account = new SavingAccount(2, sut, 1.00);
        final int initialSize = sut.getAccounts().size();

        //when
        sut.saveAccountForClient(account);

        //then
        assertAll(
                () -> assertEquals(initialSize+1, sut.getAccounts().size()),
                () -> assertTrue(sut.getAccounts().contains(account))
        );
    }

    @Test
    public void shouldGetIllegalArgumentExceptionWhenSavingAccountWithAnotherClient(){
        //given
        final int id = 1;
        final double amount = 1.00;
        final Client sut = new Client(1, "test");
        final SavingAccount account = new SavingAccount(id, sut, amount);
        final Client newClient = new Client(2, "new client");

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> newClient.saveAccountForClient(account))
                .withMessage("The account belongs to another client");
    }

}
