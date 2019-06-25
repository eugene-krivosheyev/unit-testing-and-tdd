package com.acme.banking.dbo.service;

import java.util.UUID;

public interface TxLog {
    void log(double amount, UUID fromAccountId);
}
