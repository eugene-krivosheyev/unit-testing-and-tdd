package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.ProcessingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessingServiceTest {
    @Mock private Cash mockCash;
    private AccountRepository stubRepository;
    private Account account1;
    private Account account2;
    private ProcessingService sut;
    private final UUID fromAccountId = UUID.randomUUID();
    private final UUID toAccountId = UUID.randomUUID();

    @BeforeClass
    public static void setUpEarly() {

    }

    @Before
    public void setUp() {
        mockCash = mock(Cash.class);

        /*
        stubRepository = new MockitoRepoBuilder()
                .withAccount()
                    .withClient()
                        .withId(0)
                        .withName("abc")
                .withAccount()
                    .withAmount(2)
            .build();
        */

        sut = new ProcessingService(stubRepository, mockCash);
    }

    @Test
    public void shouldCallAccountsTransferMethodsWhenTransfer() {
        sut.transfer(1., fromAccountId, toAccountId);

        verify(stubRepository).findAccountById(fromAccountId);
        verify(stubRepository).findAccountById(toAccountId);
        verify(account1).withdraw(1.);
        verify(account2).credit(1.);
        verify(stubRepository).save(account1);
        verify(stubRepository).save(account2);
    }
}
