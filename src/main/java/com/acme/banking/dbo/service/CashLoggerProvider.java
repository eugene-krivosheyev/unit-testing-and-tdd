package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.CashInternalLogger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CashLoggerProvider {

    private List<BiConsumer<Double, Integer>> loggersList = new ArrayList<>();

    public CashLoggerProvider() {
        loggersList.add(CashInternalLogger::log);
    }

    public BiConsumer<Double, Integer> getLogger(){
       return loggersList.get(0);
    }

}
