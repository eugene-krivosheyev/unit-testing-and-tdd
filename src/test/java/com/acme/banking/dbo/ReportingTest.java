package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    public static final String BRANCH_NAME = "Branch Name";

    private Branch branchStub;
    private Reporting sut;

    @Before
    public void setUp() {
        branchStub = mock(Branch.class);
        sut = new Reporting(branchStub);
    }

    @Test
    public void shouldReportHeaderForBank() {
        assertThat(sut.getReport()).contains("# Report");
    }

    @Test
    public void shouldReportHeaderForBranch() {
        when(branchStub.getName()).thenReturn(BRANCH_NAME);
        assertThat(sut.getReport())
                .contains("## Branch: " + BRANCH_NAME);
    }


    @Test @Ignore
    public void shouldReportClientsAmountWhenSingleAccount() {

    }

    @Test
    public void shouldReportHeadersForAllBranchHierarchy() {
        //??????
        assertThat(sut.getReport())
                .isEqualTo("# Report" +
                        " ## Branch: 1" +
                        "  ### Branch: 1.1" +
                        "  ### Branch: 1.2" +
                        "   #### Branch: 1.2.1" +
                        " ## Branch: 2");
    }
}
