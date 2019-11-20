package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSavePropertiesWhenCreatedAndNotNullAndNotEmptyAllDataAndTwoAccounts() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsIds = new ArrayList();
        UUID firstAccountId = UUID.randomUUID();
        UUID secondAccountId = UUID.randomUUID();
        stubAccountsIds.add(firstAccountId);
        stubAccountsIds.add(secondAccountId);
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion

        //region then
        assertEquals(stubId, sut.getId());
        assertEquals(dummyName, sut.getName());
        assertEquals(stubAccountsIds, sut.getAccountIds());
        assertEquals(2, stubAccountsIds.size());
        assertTrue(stubAccountsIds.contains(firstAccountId));
        assertTrue(stubAccountsIds.contains(secondAccountId));
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndEmptyNameAndNotNullOthers() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "";
        Collection<UUID> stubAccountsIds = new ArrayList();
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNullIdAndNotNullOthersAndNotEmptyNameAnd() {
        //region given
        UUID stubId = null;
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsIds = new ArrayList();
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNullNameAndNotNulOthers() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = null;
        Collection<UUID> stubAccountsIds = new ArrayList();
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionWhenCreatedAndNullAccountsAndNotNulOthers() {
        //region given
        UUID stubId = UUID.randomUUID();
        String dummyName = "dummy client name";
        Collection<UUID> stubAccountsIds = null;
        //endregion

        //region when
        Client sut = new Client(stubId, dummyName, stubAccountsIds);
        //endregion
    }

}
