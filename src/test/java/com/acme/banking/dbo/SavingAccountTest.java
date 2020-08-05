package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class SavingAccountTest {
    @Test
    public void shouldNotCreateWhenIdIsNull() {
        try {
            final SavingAccount sut = new SavingAccount(null, null, 0);
            fail();
        } catch (IllegalArgumentException e) { }
    }
}
