package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.error.EmptyBranchException;

public interface ReportingService {
    String getReport(Branch branch) throws EmptyBranchException;
}
