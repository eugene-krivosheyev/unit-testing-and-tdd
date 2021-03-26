package com.acme.banking.dbo;

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
}
