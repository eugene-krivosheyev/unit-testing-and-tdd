package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.AccountRepository;

import java.util.NoSuchElementException;

public class ReportingService implements AbstractReportingService {
    private AccountRepository accountRepository;

    public ReportingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String getReportForAccount(long id) {
        final String header2 = "## ";
        try {
            final Account account = accountRepository.findAccountById(id);
            return header2 + "*" + account.getId() + "* " + account.getAmount();
        } catch (NoSuchElementException e) {
            return header2 + "<No Account for id " + id;
        }
    }
}
