package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingWithBranchTest {
    private Reporting sut;
    private Branch stubBranch;
    private String report;

    @Before
    public void setUp()  {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
    }

    @Test
    public void shouldGetReportWhenBranchHasName() {
        //region when
        when(stubBranch.getName()).thenReturn("BrunchName");
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# BrunchName");
        //endregion
    }
    @Test
    public void shouldGetReportWhenBranchHasNoName() {
        //region when
        when(stubBranch.getName()).thenReturn(null);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# ");
        //endregion
    }

}
