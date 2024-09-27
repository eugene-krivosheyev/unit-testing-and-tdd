package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.List;

public class CashInternalLogger {
    private static final List<CashTransaction> cashTransactions = new ArrayList<>();
    public static void log(double amount, Integer fromAccountId) {
        cashTransactions.add(new CashTransaction().setAmount(amount).setFromAccountId(fromAccountId));
    }
}
