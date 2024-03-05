package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;

public class AccountToMarkdownConverter implements MarkDownConverter<Account> {
    @Override
    public String toMarkdown(Account object) {
        return "Account balance: " + object.getAmount();
    }
}
