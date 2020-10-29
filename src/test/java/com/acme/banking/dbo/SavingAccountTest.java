package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.SavingAccountBuider;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SavingAccountTest {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSavingAccountCreatedWhenCorrectParams() {
        SavingAccount sut = new SavingAccountBuider()
                .Build();

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals(1, sut.getAmount(), 0000);
        Assert.assertNotNull(sut.getClient());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is Null");

        SavingAccount sut = new SavingAccountBuider()
                .SetId(null)
                .Build();
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("client is Null");
        SavingAccount sut = new SavingAccountBuider()
                .SetClient(null)
                .Build();
    }

    @Test
    public void shouldNotCreateWhenAmountIsNotCorrect() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("amount is not correct");
        SavingAccount sut = new SavingAccountBuider()
                .SetAmount(-1)
                .Build();
    }
}
