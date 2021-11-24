package com.acme.banking.dbo.repositories;

import com.acme.banking.dbo.domain.Branch;

public interface BranchRepository {

    Branch getBranchById(int branchId);

}