package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ReportingUnitTest {

    @Test
    void shouldReturnEmptyReportWithNoAccountWhenBranchDoesNotHaveAccounts() {

        Branch dummyBranch = mock(Branch.class);
        Reporting sut = new Reporting();

        assertThat(sut.getReport(dummyBranch)).contains("(no accounts)");

    }

}