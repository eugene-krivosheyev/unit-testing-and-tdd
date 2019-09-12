package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Branch;

public interface BranchRepository {
    Branch findBranchById(int id);
}
