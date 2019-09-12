package com.acme.banking.dbo;

import com.acme.banking.dbo.builder.TestBranchRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.error.EmptyBranchException;
import com.acme.banking.dbo.service.ReportingServiceImpl;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ReportingServiceTest {

    @Test
    public void shouldGetReportForFilialWhenChildrenBranchesEmpty() throws EmptyBranchException {
        TestBranchRepository branchRepository = new TestBranchRepository.Builder().build();
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch = branchRepository.getRepository().findBranchById(1);
        String result = reportingService.getReport(branch);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# " + branch.getId() + ": " + branch.getName() + " " + branch.getAccounts().size());
    }

    @Test
    public void shouldGetReportForFilialWithAccountsWhenChildrenBranchesEmpty() throws EmptyBranchException {
        TestBranchRepository branchRepository = new TestBranchRepository.Builder().build();
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch = branchRepository.getRepository().findBranchById(4);
        Account account = branch.getAccounts().get(0);
        Client client = account.getClient();
        String result = reportingService.getReport(branch);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# " + branch.getId() + ": " + branch.getName() + " " + branch.getAccounts().size() + "\n" +
                        " - " + account.getAmount() + ", Client: "+ client.getName());
    }

    @Test
    public void shouldGetReportForFilialLevelOneWhenChildrenBranchesExist() throws EmptyBranchException {
        TestBranchRepository branchRepository = new TestBranchRepository.Builder().build();
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch = branchRepository.getRepository().findBranchById(2);
        String result = reportingService.getReport(branch);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# 1: Филиал1 0\n" +
                        " ## 2: Филиал2 0\n" +
                        " ## 3: Филиал3 0");
    }

    @Test
    public void shouldGetReportForFilialWhenChildrenBranchesExist() throws EmptyBranchException {
        TestBranchRepository branchRepository = new TestBranchRepository.Builder().build();
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch = branchRepository.getRepository().findBranchById(3);
        String result = reportingService.getReport(branch);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# 1: Филиал1 0\n" +
                        " ## 2: Филиал2 0\n" +
                        "  ### 4: Филиал4 0\n" +
                        "  ### 5: Филиал5 0\n" +
                        " ## 3: Филиал3 0\n" +
                        "  ### 6: Филиал6 0\n" +
                        "  ### 7: Филиал7 0");
    }

    @Test(expected = EmptyBranchException.class)
    public void shouldReturnEmptyReportWhenBrunchIsNull() throws EmptyBranchException {
        ReportingServiceImpl reportingService = new ReportingServiceImpl();
        reportingService.getReport(null);
    }
}