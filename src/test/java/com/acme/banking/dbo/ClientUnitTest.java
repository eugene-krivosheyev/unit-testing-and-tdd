package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientUnitTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private UUID stubId;
    private String dummyName = "dummy client name";
    private SavingAccount stubAccount1;
    private SavingAccount stubAccount2;
    private Collection<SavingAccount> stubAccounts = new ArrayList<>();

    @Before
    public void setUp() {
        stubId = UUID.randomUUID();
        dummyName = "dummy client name";
        stubAccount1 = new SavingAccount(UUID.randomUUID(), 100.);
        stubAccount2 = new SavingAccount(UUID.randomUUID(), 0.);
        stubAccounts.add(stubAccount1);
        stubAccounts.add(stubAccount2);
    }

    @Test
    public void shouldSavePropertiesWhenCreatedAndAllParamsCorrectly() {
        //region when
        Client sut = new Client(stubId, dummyName, stubAccounts);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(dummyName, sut.getName());
        assertEquals(2, sut.getAccounts().size());
        assertTrue(sut.getAccounts().contains(stubAccount1));
        assertTrue(sut.getAccounts().contains(stubAccount2));
        //endregion
    }

    @Test
    public void shouldExceptionWhenCreatedAndEmptyName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must not null and not empty");

        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "";
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccounts);
        //endregion
    }

    @Test
    public void shouldExceptionWhenCreatedAndNullId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("id must not null");

        //region given
        UUID stubId = null;
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccounts);
        //endregion
    }

    @Test
    public void shouldExceptionWhenCreatedAndNullName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("name must not null and not empty");
        //region given
        String dummyName = null;
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccounts);
        //endregion
    }

    @Test
    public void shouldExceptionWhenCreatedAndNullAccounts() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("accounts must not null");
        //region given
        Collection<SavingAccount> stubAccountsIds = null;
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion
    }

}
