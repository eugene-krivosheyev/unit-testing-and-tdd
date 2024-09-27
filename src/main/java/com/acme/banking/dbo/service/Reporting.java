package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {

    private static final MarkDownConverter<Branch> branchConverter = new BranchToMarkdownConverter();

    public String getReport(Branch rootBranch) {
        if (rootBranch == null){
            return "Empty report";
        }
        return branchConverter.toMarkdown(rootBranch);
    }
}
