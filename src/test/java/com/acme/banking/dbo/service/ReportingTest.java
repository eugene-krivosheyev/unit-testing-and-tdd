package com.acme.banking.dbo.service;
import com.acme.banking.dbo.domain.Branch;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ReportingTest {
    @Test
    public void shouldGetReportWhenBranchNotEmpty(){
        Reporting sut = new Reporting();
        Branch stubBranch= mock(Branch.class);
        String report  = sut.getReport(stubBranch);
        assertThat(report).contains("## clientName");

    }
    @Test
    public void shouldGetReportWhenBranchIsEmpty(){
        Reporting sut = new Reporting();
        Branch stubBranch = mock(Branch.class);
        String report = sut.getReport(stubBranch);
        assertThat(report).isEqualTo("# BrunchName");
    }
}
