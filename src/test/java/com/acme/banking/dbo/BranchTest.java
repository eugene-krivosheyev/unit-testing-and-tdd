package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BranchTest {
    @Test
    public void shouldNotModifyCollection() {
        Client client = new Client(1, "DummyClient");
        Account savingAccount = new SavingAccount(1, client, 1.0);
        List<Account> accounts = Collections.singletonList(savingAccount);
        Branch branch = new Branch(accounts);
        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> branch.getAccounts().add(savingAccount));
        assertEquals(thrown.getMessage(), null);
    }

}
