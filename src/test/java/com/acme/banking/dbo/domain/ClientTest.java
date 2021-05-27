package com.acme.banking.dbo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Test suite")
public class ClientTest {

    public static final int CLIENT_ID = 1;
    public static final String CLIENT_NAME = "dummy client name";

    @Test
//    @Disabled("temporary disabled")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        //endregion

        //region when
        Client sut = new Client(CLIENT_ID, CLIENT_NAME);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
 /*       assertAll("Client store its properties",
                () -> assertEquals(CLIENT_ID, sut.getId()),
                () -> assertEquals(CLIENT_NAME, sut.getName())
        );*/

        //Hamcrest:
 /*       assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(CLIENT_ID)),
                hasProperty("name", is(CLIENT_NAME))
        ));*/

        //AssertJ:
        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", CLIENT_ID)
                .hasFieldOrPropertyWithValue("name", CLIENT_NAME);
        //endregion
    }

    @ParameterizedTest
    @MethodSource("exceptionsTestData")
    public void shouldThrowExceptionWhenIdIsNegative(ClientTestData data){
        Assertions.assertThatThrownBy(() -> new Client(data.getClientId(), data.getName())).hasMessage(data.getExceptionText());
    }

    static Stream<ClientTestData> exceptionsTestData(){
        return Stream.of(new ClientTestData(-1, "Dummy", "id should be positive"),
                        new ClientTestData(1, null, "name should not be null or empty"),
                        new ClientTestData(1, "", "name should not be null or empty"));
    }

    static class ClientTestData{
        private int clientId;
        private String name;
        private String exceptionText;


        ClientTestData(int clientId, String name, String exceptionText){
            this.clientId = clientId;
            this.name = name;
            this.exceptionText = exceptionText;
        }

        public int getClientId() {
            return clientId;
        }

        public String getName() {
            return name;
        }

        public String getExceptionText() {
            return exceptionText;
        }

        @Override
        public String toString() {
            return "ClientTestData{" +
                    "clientId=" + clientId +
                    ", name='" + name + '\'' +
                    ", exceptionText='" + exceptionText + '\'' +
                    '}';
        }
    }

    @Disabled
    @Test
    public void shouldContainOnlyOwnAccountsIntegration(){
        Client sut = new Client(CLIENT_ID, CLIENT_NAME);
        Client dummyClient = new Client(CLIENT_ID, CLIENT_NAME);
        SavingAccount dummyAccount = new SavingAccount(2,dummyClient,1);
        Assertions.assertThatThrownBy(() -> sut.addAccount(dummyAccount)).hasMessage("This account has another client!");
    }

    @Test
    public void shouldContainOnlyOwnAccountsUnit(){
        Client sut = new Client(CLIENT_ID, CLIENT_NAME);
        Client dummyClient = mock(Client.class);
        SavingAccount stubSavingAccount = mock(SavingAccount.class);
        when(stubSavingAccount.getClient()).thenReturn(dummyClient);

        Assertions.assertThatThrownBy(() -> sut.addAccount(stubSavingAccount)).hasMessage("This account has another client!");
    }

    @Test
    public void shouldStoreAccountWhenAdd(){
        Client sut = new Client(CLIENT_ID, CLIENT_NAME);
        SavingAccount mockAccount = mock(SavingAccount.class);
        when(mockAccount.getClient()).thenReturn(sut);
        sut.addAccount(mockAccount);
        assert (sut.getAccounts().contains(mockAccount));
    }

}
