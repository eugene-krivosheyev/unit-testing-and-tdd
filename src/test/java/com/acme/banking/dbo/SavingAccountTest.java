package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.SavingAccountBuilder;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.UUID;

public class SavingAccountTest {

    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    public static final int NEGATIVE_AMOUNT = -1;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSavingAccountCreatedWhenCorrectParams() {
        SavingAccount sut = new SavingAccountBuilder()
                .build();

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals(1, sut.getAmount(), 0.001);
        Assert.assertNotNull(sut.getClient());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is Null");

        SavingAccount sut = SavingAccountBuilder.create()
                .setId(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("client is Null");

        SavingAccountBuilder.create()
                .setClient(null)
                .build();
    }

    @Test
    public void shouldNotCreateWhenAmountIsNotCorrect() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("amount is not correct");

        SavingAccountBuilder.create()
                .setAmount(NEGATIVE_AMOUNT)
                .build();
    }
}
