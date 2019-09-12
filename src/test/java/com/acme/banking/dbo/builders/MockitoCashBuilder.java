package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.domain.Cash;

import static org.mockito.Mockito.mock;

public class MockitoCashBuilder {

    public Cash build(){
        return mock(Cash.class);
    }
}
