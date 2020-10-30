package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.ClientBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldClientCreatedWhenCorrectParams() {
        Client sut = ClientBuilder.Create()
                .Build();

        Assert.assertEquals(ID_STUB, sut.getId());
        Assert.assertEquals("dummy", sut.getName());
    }

    @Test
    public void shouldNotCreateWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("id is Nul");

        Client sut = ClientBuilder.Create()
                .SetId(null)
                .Build();
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Null");

        Client sut = ClientBuilder.Create()
                .SetName(null)
                .Build();
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("name is Empty");

        Client sut = ClientBuilder.Create()
                .SetName("")
                .Build();
    }

    @Test
    public void shouldReturnSameAccountsWhenSetAccounts() {
        Account firtsAccountDoubler = mock(SavingAccount.class);
        Account secondAccountDoubler = mock(SavingAccount.class);
        List<Account> expectedAccounts = new ArrayList();
        expectedAccounts.add(firtsAccountDoubler);
        expectedAccounts.add(secondAccountDoubler);

        Client sut = ClientBuilder.Create()
                                  .SetAccounts(expectedAccounts)
                                  .Build();

        List<Account> actualAccounts = sut.getAccounts();

        Assert.assertEquals(expectedAccounts, actualAccounts);

    }
}
