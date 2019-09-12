package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.AuditService;
import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BranchesReportingTest {
    @Mock private AccountRepository accounts;
    @Mock private AuditService audit;

    @Test
    public void shouldGetReportForFilialWhenClientsNotExists() {
        final Reporting reportingService = new Reporting(accounts, audit);

        Branch filialBranch = new Branch(
                1, "Филиал № 15", 4, emptyList());

        assertThat(reportingService.getReport(filialBranch))
                .isNotEmpty()
                .contains("#### 1: Филиал № 15");
    }
}
