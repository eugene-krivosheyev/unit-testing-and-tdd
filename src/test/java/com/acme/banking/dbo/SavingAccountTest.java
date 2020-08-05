package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

public class SavingAccountTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        final SavingAccount sut = new SavingAccount(null, null, 0);
    }
}
