package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Branch;
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

        String report = sut.getReport(branchStub);

        assertEquals("# Branch #1", report);
    }

    @Test
    public void shouldGetReportWhenBranchWithAccountsWithClients() {
        Reporting sut = new Reporting();
        Branch branchStub = mock(Branch.class);

        assertEquals(
                "# Branch #1" +
                "## Account #1 (100.)" +
                "### Client #1 : name1" +
                "### Client #1 : name2" +
                "### Client #1 : name3" +
                "## Account #2 (100.)",
                "## Branch #2" +
                "# Branch #3" +
                "(empty)" +
                sut.getReport(branchStub)
        );

//        AccountRepository repoStub = mock();
//        Reporting reportingService = new Reporting(repoStub);
//        String report = reportingService.getReport();
//        assertEquals("kjghdkfjgh", report);
    }

    @Test
    public void shouldGetEmptyReportWhenEmptyBranchOnly() {
        assertEquals(
                "# Branch #1" +
                "(empty)"
                ,report);
    }

    @Test
    public void shouldGetEmptyReportWhenEmptyTwoBranches() {
        assertEquals(
                "# Branch #1" +
                "(empty)" +
                "# Branch #2" +
                "(empty)"
                ,report);
    }

    @Test
    public void shouldGetEmptyReportWhenEmptyBranchesHierarchy() {
        assertEquals(
                "# Branch #1" +
                "## Branch #1-1" +
                "(empty)"
                ,report);
    }

    @Test
    public void shouldGetEmptyReportWhenBranchOnlyWithEmptyAccountOnly() {
        assertEquals(
                "# Branch #1" +
                "## Account #1 (0.)" +
                "(empty)"
                ,report);
    }
}
