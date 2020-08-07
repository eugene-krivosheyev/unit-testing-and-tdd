package com.acme.banking.dbo.service;

public interface TxManager {
    void start();
    void commit();
    void rollback();
}
