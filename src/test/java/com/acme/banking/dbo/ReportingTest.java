package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    @Test
    public void shouldGetReportWhenEmptyBranch() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        when(branchStub.getAccounts()).thenReturn(emptySet());
        when(branchStub.getName()).thenReturn("Branch #1");

        assertEquals("# Branch #1",
                sut.getReport(branchStub));
    }

    @Test
    public void shouldGetReportWhenEmptyAccount() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);
        when(branchStub.getChildren()).thenReturn(Collections.singleton(branchStub));
        when(branchStub.getName()).thenReturn("# Branch #1");

        assertEquals(
                "# Branch #1",
                sut.getReport(branchStub)
        );
    }

    @Test
    public void shouldGetReportWhenAccountWithoutAmount() {
        Reporting sut = new Reporting();
        Account accountStub = mock(Account.class);
        Branch branchStub = mock(Branch.class);
        when(branchStub.getChildren()).thenReturn(Collections.singleton(branchStub));
        when(branchStub.getAccounts()).thenReturn(Collections.singleton(accountStub));
        when(branchStub.getName()).thenReturn("# Branch #1");
        when(accountStub.getName()).thenReturn("## Account #1");

        assertEquals(
                "# Branch #1"
                        + "## Account #1",
                sut.getReport(branchStub)
        );
    }

    @Test
    public void shouldGetReportWhenAccountWithAmount() {
        Reporting sut = new Reporting();
        Account accountStub = mock(Account.class);
        Branch branchStub = mock(Branch.class);
        when(branchStub.getChildren()).thenReturn(Collections.singleton(branchStub));
        when(branchStub.getAccounts()).thenReturn(Collections.singleton(accountStub));
        when(branchStub.getName()).thenReturn("# Branch #1");
        when(accountStub.getName()).thenReturn("## Account #1");
        when(accountStub.getAmount()).thenReturn(100.);

        assertEquals(
                "# Branch #1"
                        + "## Account #1 (100.0)",
                sut.getReport(branchStub)
        );
    }

    @Test
    public void shouldGetReportWhenBranchWithEmptyClient() {
        Reporting sut = new Reporting();
        Account accountStub = mock(Account.class);
        Branch branchStub = mock(Branch.class);
        Client clientStub = mock(Client.class);
        when(branchStub.getChildren()).thenReturn(Collections.singleton(branchStub));
        when(branchStub.getAccounts()).thenReturn(Collections.singleton(accountStub));
        when(branchStub.getName()).thenReturn("# Branch #1");
        when(accountStub.getName()).thenReturn("## Account #1");
        when(accountStub.getAmount()).thenReturn(100.);
        when(accountStub.getClient()).thenReturn(clientStub);
        when(clientStub.getName()).thenReturn("### Client #1");

        assertEquals(
                "# Branch #1\r\n" +
                        "## Account #1 (100.0)\r\n" +
                        "### Client #1\r\n",
                sut.getReport(branchStub)
        );
    }
}
