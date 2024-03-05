package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountToMarkdownConverter;
import com.acme.banking.dbo.service.BranchToMarkdownConverter;
import com.acme.banking.dbo.service.Reporting;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTesting {


    @Test
    void givenReportingServiceWithNullBranchShouldReportEmptyReport() {
        var reportingService = new Reporting();

        assertEquals("Empty report", reportingService.getReport(null));
    }

    @Test
    void givenReportWithBranchWithoutAccountAndChildBranchShouldReport() {
        var reportingService = new Reporting();
        var mockedBranch = mock(Branch.class);
        when(mockedBranch.getName()).thenReturn("SomeName");

        assertEquals("#SomeName", reportingService.getReport(mockedBranch));
    }

    @Test
    void givenReportWithBranchWithAccountAndWithoutChildBranchShouldReport() {
        var reportingService = new Reporting();
        var mockedBranch = mock(Branch.class);
        when(mockedBranch.getName()).thenReturn("SomeName");
        var mockedAccount = mock(SavingAccount.class);
        when(mockedAccount.getAmount()).thenReturn(100d);
        when(mockedBranch.getAccounts()).thenReturn(List.of(mockedAccount));

        assertEquals("#SomeName Account balance: 100.0", reportingService.getReport(mockedBranch));
    }


    @Test
    void givenReportWithBranchWithAccountAndChildBranchShouldReport() {
        var reportingService = new Reporting();
        var mockedBranch = mock(Branch.class);
        when(mockedBranch.getName()).thenReturn("SomeName");
        var mockedAccount = mock(SavingAccount.class);
        when(mockedAccount.getAmount()).thenReturn(100d);
        when(mockedBranch.getAccounts()).thenReturn(List.of(mockedAccount));
        when(mockedBranch.getChildren()).thenReturn(List.of(mockedBranch));


        assertEquals("#SomeName Account balance: 100.0 ##SomeName", reportingService.getReport(mockedBranch));
    }



    @Nested
    class BranchConverterToMarkdown {

        @Test
        void givenBranchShouldTranslateToMarkdown() {
            var converter = new BranchToMarkdownConverter();
            var mockedBranch = mock(Branch.class);
            when(mockedBranch.getName()).thenReturn("someName");

            assertEquals("#someName", converter.toMarkdown(mockedBranch));
        }

        @Test
        void givenBranchWithBranchShpuldTranslateToMarkdown() {
            var converter = new BranchToMarkdownConverter();
            var mockedBranch = mock(Branch.class);
            when(mockedBranch.getName()).thenReturn("someName");
            when(mockedBranch.getChildren()).thenReturn(List.of(mockedBranch));

            assertEquals("#someName ##someName", converter.toMarkdown(mockedBranch));
        }

    }


    @Nested
    class AccountConverterToMarkdown {

        @Test
        void givenAccountShouldConvertToAccount() {
            var converter = new AccountToMarkdownConverter();
            var mockedAccount = mock(SavingAccount.class);
            when(mockedAccount.getAmount()).thenReturn(100d);

            assertEquals("Account balance: 100.0", converter.toMarkdown(mockedAccount));
        }
    }
}
