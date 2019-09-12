package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public interface ReportingService {
    String getReport(Branch branch);
}
