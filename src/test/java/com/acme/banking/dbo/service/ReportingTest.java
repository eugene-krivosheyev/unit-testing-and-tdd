package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportingTest {

//    @Test
//    public void shouldGetReportWhenBranchWithAccountsWithClients() {
//        Reporting sut = new Reporting();
//        Branch branchStub = mock(Branch.class);
//
//        assertEquals(
//                "# Branch #1" +
//                        "## Account #1 (100.)" +
//                        "### Client #1" +
//                        "### Client #1" +
//                        "### Client #1" +
//                        "## Account #2 (100.)",
//                sut.getReport(branchStub)
//        );
//    }

    @Test
    void shouldReturnReportWithOneBranchWithoutChildBranches_when_branchHasNoChildBranchesAndNoAccounts() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        when(branchStub.getName()).thenReturn("1");
        when(branchStub.getChildren()).thenReturn(Collections.emptyList());
        when(branchStub.getAccounts()).thenReturn(Collections.emptyList());

        String report = sut.getReport(branchStub);

        assertEquals("# Branch #1", report);
    }

    @Test
    void shouldReturnReportWithOneBranchWithOneChildBranch_when_branchHasOneChildBranchesAndNoAccounts() {
        Reporting sut = new Reporting();
        Branch rootBranchStub = mock(Branch.class);
        Branch childBranchStub = mock(Branch.class);
        when(rootBranchStub.getName()).thenReturn("1");
        when(rootBranchStub.getChildren()).thenReturn(Lists.newArrayList(childBranchStub));
        when(rootBranchStub.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub.getName()).thenReturn("1-1");
        when(childBranchStub.getChildren()).thenReturn(Collections.emptyList());
        when(childBranchStub.getAccounts()).thenReturn(Collections.emptyList());

        String report = sut.getReport(rootBranchStub);

        assertEquals(
                "# Branch #1\n" +
                        "## Branch #1-1",
                report);
    }

    @Test
    void shouldReturnReportWithOneBranchWithTwoChildBranches_when_branchHasTwoChildBranchesAndNoAccounts() {
        Reporting sut = new Reporting();
        Branch rootBranchStub = mock(Branch.class);
        Branch childBranchStub1 = mock(Branch.class);
        Branch childBranchStub2 = mock(Branch.class);
        when(rootBranchStub.getName()).thenReturn("1");
        when(rootBranchStub.getChildren()).thenReturn(Lists.newArrayList(childBranchStub1, childBranchStub2));
        when(rootBranchStub.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub1.getName()).thenReturn("1-1");
        when(childBranchStub1.getChildren()).thenReturn(Collections.emptyList());
        when(childBranchStub1.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub2.getName()).thenReturn("1-2");
        when(childBranchStub2.getChildren()).thenReturn(Collections.emptyList());
        when(childBranchStub2.getAccounts()).thenReturn(Collections.emptyList());

        String report = sut.getReport(rootBranchStub);

        assertEquals(
                "# Branch #1\n" +
                        "## Branch #1-1\n" +
                        "## Branch #1-2",
                report);
    }
    
    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasOneAccountAndNoChildBranches() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        Account account = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);

        List<Account> accounts = Lists.newArrayList(account);

        when(branchStub.getName()).thenReturn("1");
        when(branchStub.getAccounts()).thenReturn(accounts);

        String report = sut.getReport(branchStub);

        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account.getId() + " (100.0)",
                report
        );
    }

    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasTwoAccountsAndNoChildBranches() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        Account account1 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);
        Account account2 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  200.0d);

        List<Account> accounts = Lists.newArrayList(account1, account2);

        when(branchStub.getName()).thenReturn("1");
        when(branchStub.getAccounts()).thenReturn(accounts);

        String report = sut.getReport(branchStub);

        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account1.getId() + " (100.0)\n" +
                        "## Account #" + account2.getId() + " (200.0)",
                report
        );
    }

    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasTwoAccountsAndOneChildBranchWithNoAccounts() {
        Reporting sut = new Reporting();
        Branch rootBranchStub = mock(Branch.class);
        Account account1 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);
        Account account2 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  200.0d);
        List<Account> accounts = Lists.newArrayList(account1, account2);

        Branch childBranchStub = mock(Branch.class);
        when(childBranchStub.getName()).thenReturn("1-1");
        when(childBranchStub.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub.getChildren()).thenReturn(Collections.emptyList());

        when(rootBranchStub.getName()).thenReturn("1");
        when(rootBranchStub.getAccounts()).thenReturn(accounts);
        when(rootBranchStub.getChildren()).thenReturn(Lists.newArrayList(childBranchStub));

        String report = sut.getReport(rootBranchStub);
        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account1.getId() + " (100.0)\n" +
                        "## Account #" + account2.getId() + " (200.0)\n" +
                        "## Branch #" + childBranchStub.getName(),
                report
        );
    }

    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasTwoAccountsAndTwoChildBranchesWithNoAccounts() {
        Reporting sut = new Reporting();
        Branch rootBranchStub = mock(Branch.class);
        Account account1 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);
        Account account2 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  200.0d);
        List<Account> accounts = Lists.newArrayList(account1, account2);

        Branch childBranchStub1 = mock(Branch.class);
        when(childBranchStub1.getName()).thenReturn("1-1");
        when(childBranchStub1.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub1.getChildren()).thenReturn(Collections.emptyList());

        Branch childBranchStub2 = mock(Branch.class);
        when(childBranchStub2.getName()).thenReturn("1-2");
        when(childBranchStub2.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub2.getChildren()).thenReturn(Collections.emptyList());

        when(rootBranchStub.getName()).thenReturn("1");
        when(rootBranchStub.getAccounts()).thenReturn(accounts);
        when(rootBranchStub.getChildren()).thenReturn(Lists.newArrayList(childBranchStub1, childBranchStub2));

        String report = sut.getReport(rootBranchStub);
        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account1.getId() + " (100.0)\n" +
                        "## Account #" + account2.getId() + " (200.0)\n" +
                        "## Branch #" + childBranchStub1.getName() + "\n" +
                        "## Branch #" + childBranchStub2.getName(),
                report
        );
    }

    @Test
    void shouldReturnReportWithAllAccountsInBranch_when_branchHasTwoAccountsAndTwoChildBranches_when_firstChildBranchHasTwoAccounts_and_secondChildChildBranchHasNoAccounts() {
        Reporting sut = new Reporting();
        Branch rootBranchStub = mock(Branch.class);
        Account account1 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  100.0d);
        Account account2 = new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  200.0d);
        List<Account> accounts = Lists.newArrayList(account1, account2);

        Branch childBranchStub1 = mock(Branch.class);
        when(childBranchStub1.getName()).thenReturn("1-1");
        List<Account> childBranchStub1Accounts = Lists.newArrayList(
                new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client1"),  300.0d),
                new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Client2"),  400.0d));
        when(childBranchStub1.getAccounts()).thenReturn(childBranchStub1Accounts);
        when(childBranchStub1.getChildren()).thenReturn(Collections.emptyList());

        Branch childBranchStub2 = mock(Branch.class);
        when(childBranchStub2.getName()).thenReturn("1-2");
        when(childBranchStub2.getAccounts()).thenReturn(Collections.emptyList());
        when(childBranchStub2.getChildren()).thenReturn(Collections.emptyList());

        when(rootBranchStub.getName()).thenReturn("1");
        when(rootBranchStub.getAccounts()).thenReturn(accounts);
        when(rootBranchStub.getChildren()).thenReturn(Lists.newArrayList(childBranchStub1, childBranchStub2));

        String report = sut.getReport(rootBranchStub);
        assertEquals(
                "# Branch #1\n" +
                        "## Account #" + account1.getId() + " (100.0)\n" +
                        "## Account #" + account2.getId() + " (200.0)\n" +
                        "## Branch #" + childBranchStub1.getName() + "\n" +
                        "### Account #" + childBranchStub1Accounts.get(0).getId() + " (300.0)\n" +
                        "### Account #" + childBranchStub1Accounts.get(1).getId() + " (400.0)\n" +
                        "## Branch #" + childBranchStub2.getName(),
                report
        );
    }
}
