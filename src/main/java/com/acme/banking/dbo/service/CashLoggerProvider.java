package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.CashInternalLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CashLoggerProvider {

    private List<BiConsumer<Double, Integer>> loggersList = new ArrayList<>();

    public CashLoggerProvider() {
        loggersList.add(CashInternalLogger::log);
    }

    public void log(double amount, Integer fromAccountId){
        loggersList.get(0).accept(amount,fromAccountId);
    }

}
