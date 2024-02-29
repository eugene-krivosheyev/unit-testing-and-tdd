package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import static com.acme.banking.dbo.domain.Errors.CLIENT_EMPTY_NAME_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.CLIENT_NEGATIVE_ID_MESSAGE;
import com.acme.banking.dbo.domain.SavingAccount;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Client Test")
public class ClientTest {

    @Test
    @DisplayName("Negative clientId testCase")
    public void shouldThrowExceptionWhenClientIdIsNegative() {
        final int clientId = -1;
        final String clientName = "dummy client name";

        var nullClientIdEx = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
        //если MESSAGE не часть контракта, то лучше не завязываться на строку сообщения (i18, frontend, etc.)
        //фиксировать лучше тип Exception-а
        assertEquals(CLIENT_NEGATIVE_ID_MESSAGE, nullClientIdEx.getMessage());
    }

    @Test
    @DisplayName("Negative clientId with fail testCase")
    public void shouldThrowExceptionWhenClientIdIsNegativeWithFail() {
        final int clientId = -1;
        final String clientName = "dummy client name";

        try {
            Client sut = new Client(clientId, clientName);
            fail();
        } catch (IllegalArgumentException exception) {
            assertEquals(CLIENT_NEGATIVE_ID_MESSAGE, exception.getMessage());
        }
    }

    @Test
    @DisplayName("Null or empty clientName testCase")
    public void shouldThrowExceptionWhenClientNameIsNullOrEmpty() {
        final int clientId = 1;
        final String nullClientName = null;
        final String emptyClientName = "";

        var nullClientNameEx = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, nullClientName));
        var emptyClientNameEx = assertThrows(IllegalArgumentException.class, () -> new Client(clientId, emptyClientName));
        assertEquals(CLIENT_EMPTY_NAME_MESSAGE, nullClientNameEx.getMessage());
        assertEquals(CLIENT_EMPTY_NAME_MESSAGE, emptyClientNameEx.getMessage());
    }

    @Test
    @DisplayName("Client initialize with zero id testCase")
    public void shouldCreateClientWhenIdIsZero() {
        final int clientId = 0;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }

    @Test
    @DisplayName("Client initialize with valid params testCase")
    public void shouldCreateClientWhenParamsAreValid() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }

    @Test
    @DisplayName("Client add null account testCase")
    public void shouldThrowExceptionWhenAddNullAccount() {
        final int clientId = 0;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        final Account account = null;

        assertThrows(IllegalStateException.class, () -> sut.addAccount(account));
    }

    @Test
    @DisplayName("Client add invalid client account testCase")
    public void shouldThrowExceptionWhenAddInvalidAccount() {
        final int validClientId = 0;
        final String validClientName = "dummy client name";
        final int invalidClientId = 1;
        final String invalidClientName = "dummy client2 name";

        Client validClient = new Client(validClientId, validClientName);
        Client invalidCLient = new Client(invalidClientId, invalidClientName);
        Account invalidAccount = new SavingAccount(1, invalidCLient, 100.00);
        assumeTrue(validClient != null);
        assumeTrue(invalidCLient != null);
        assumeTrue(invalidAccount != null);

        assertThrows(IllegalStateException.class, () -> validClient.addAccount(invalidAccount));
    }

    @Test
    @DisplayName("Client add duplicate account testCase")
    public void shouldThrowExceptionWhenAddAccountDuplicate() {
        final int validClientId = 0;
        final String validClientName = "dummy client name";

        Client validClient = new Client(validClientId, validClientName);
        Account existingAccount = new SavingAccount(1, validClient, 100.00);
        validClient.addAccount(existingAccount);
        Account duplicatingAccount = new SavingAccount(1, validClient, 110.00);

        assumeTrue(validClient != null);
        assumeTrue(existingAccount != null);
        assumeTrue(validClient.getAccounts().contains(existingAccount));
        assumeTrue(duplicatingAccount != null);

        assertThrows(IllegalStateException.class, () -> validClient.addAccount(duplicatingAccount));
    }

    @Test
    @DisplayName("Client add valid account testCase")
    public void shouldAddAccountWhenAccountIsValid() {
        final int clientId = 0;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        Account account = new SavingAccount(1, sut, 100.00);
        assumeTrue(sut != null);
        assumeTrue(account != null);
        assumeTrue(sut.getAccounts().size() == 0);

        assertAll(
                () -> assertDoesNotThrow(() -> sut.addAccount(account)),
                () -> assertEquals(1, sut.getAccounts().size()),
                () -> assertTrue(sut.getAccounts().contains(account))
        );
    }

}
