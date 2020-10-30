package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {

    private Branch BRANCH_MOCK;

    @Before
    public void setUp() {
        BRANCH_MOCK = mock(Branch.class);
        when(BRANCH_MOCK.getName()).thenReturn("");
        when(BRANCH_MOCK.getAccounts()).thenReturn(Collections.emptyList());
    }

    @Test
    public void shouldReturnNonEmptyStringWhenBranchNotNull() {
        Reporting sut = new Reporting();

        String result = sut.getReport(BRANCH_MOCK);

        assertFalse(result.isEmpty());
    }

    //TODO user builder
    @Test
    public void shouldReturnStringWhenBranchHasNameAndAccounts() {
        Reporting sut = new Reporting();

        String result = sut.getReport(BRANCH_MOCK);

        assertEquals(result, "# name: , accounts count: 0");
    }
}