package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

import java.util.stream.Collectors;

public class BranchToMarkdownConverter implements MarkDownConverter<Branch> {

    private static final MarkDownConverter<Account> accountConverter = new AccountToMarkdownConverter();

    @Override
    public String toMarkdown(Branch object) {
        var accountsMarkdown = "";
        if (!object.getAccounts().isEmpty()) {
            accountsMarkdown = object
                .getAccounts()
                .stream()
                .map(e -> accountConverter.toMarkdown(e))
                .collect(Collectors.joining("\n"));
        }

        if (object.getChildren().isEmpty()) {
            return "#" + object.getName() + " " + accountsMarkdown;
        }


        return "#" + object.getName() + " " + accountsMarkdown + " ##" + object
            .getChildren()
            .stream()
            .map(Branch::getName)
            .collect(Collectors.joining("\n ##"));
    }
}
