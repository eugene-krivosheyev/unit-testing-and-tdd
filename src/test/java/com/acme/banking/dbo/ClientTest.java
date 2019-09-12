package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class ClientTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private UUID stubId;
    private Client stubClient;
    private SavingAccount stubAccount;

    @Before
    public void setUp() {
        stubId = UUID.randomUUID();
        stubClient = new Client(stubId, "dummy client name");
        stubAccount = new SavingAccount(stubId, stubClient, 0);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        assertThat(stubClient.getId())
                .isEqualTo(stubId)
                .isNotNull();
        assertThat(stubClient.getName())
                .isEqualTo("dummy client name")
                .isNotNull();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingAndNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name is null");

        new Client(stubId, null);

    }

    @Test
    public void shouldThrowExceptionWhenCreatingAndNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name is empty");

        new Client(stubId, "");
    }

    @Test
    public void shouldSizeIncrementedAndAccountContainedWhenAccountAdded() {
        Assume.assumeTrue(stubClient.getAccounts().isEmpty());

        stubClient.addAccount(stubAccount);

        assertThat(stubClient.getAccounts())
                .hasSize(1)
                .contains(stubAccount);

    }

    @Test
    public void shouldSizeDecrementedAndAccountNoContainedWhenAccountRemove() {
        stubClient.addAccount(stubAccount);
        Assume.assumeFalse(stubClient.getAccounts().isEmpty());

        stubClient.removeAccount(stubAccount);

        assertThat(stubClient.getAccounts())
                .hasSize(0)
                .doesNotContain(stubAccount);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenAddAlienAccount() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("This account does not belong to the client");

        Client sut = new Client(UUID.randomUUID(), "sut");
        sut.addAccount(stubAccount);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenRemoveAccountWithClient() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("This account does not belong to the client");

        Client sut = new Client(UUID.randomUUID(), "sut");
        sut.addAccount(stubAccount);
    }

    @Test
    public void shouldSaveIdWhenCreated() {
        assertThat(stubClient.getId())
                .isSameAs(stubId);
    }

    @Test
    public void shouldSaveNameWhenCreated() {
        assertThat(stubClient.getName())
                .isSameAs("dummy client name");
    }

}
