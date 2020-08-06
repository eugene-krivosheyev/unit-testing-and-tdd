package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BranchTest {
    @Test @Ignore
    public void shouldSaveAccountsWhenCreatedBranch() {
        //region given
        int stubId = 1;
        double stubDouble = 1.0;

        List<Account> stubColAccounts = new ArrayList<>();
        Client sutClient = new Client(stubId, "dummy client name");
        SavingAccount sutSaving = new SavingAccount(stubId, sutClient, stubDouble);
        stubColAccounts.add(sutSaving);
        //endregion

        //region when
        Branch sutBrach = new Branch(stubColAccounts);
        //endregion

        //region then
        assertThat(sutBrach.getAccounts(),
                allOf(
                        equalTo(stubColAccounts),
                        notNullValue()
                ));
        //endregion
    }


}
