package com.acme.banking.dbo;

import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import com.acme.banking.dbo.service.TxLog;
import org.junit.*;

import java.util.UUID;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static final double ANY_AMOUNT = 1.0;
    private static final UUID ANY_UUID = UUID.randomUUID();
    private TxLog mockTxLog;
    private Processing sut;
    private AccountRepository accounts;

    @BeforeClass @AfterClass
    public static void setUpOnce() {

    }

    @Before @After
    public void setUp() {
        mockTxLog = mock(TxLog.class);
        accounts = mock(AccountRepository.class);
        sut = new Processing(mockTxLog);
    }

    @Test
    public void shouldLogTxWithTxLogWhenCacheOperation() {
        new AccountRepoBuilder()
                .withAccount(1, 100)
                .withAccount(2, 200)
            .build();

        AccountRepository stubRepo =
            new FakeDatabaseAccountRepositoryBuilder()
                .withAccount(
                        new AccountBuilder()
                                .withId(100)
                                .withClientId(200)
                            .build())
                .withAccount(
                        new AccountBuilder()
                                .withId()
                                .withClient() //defaults!
                            .build())
            .build();

        sut.cash(ANY_AMOUNT, ANY_UUID);

        verify(mockTxLog, times(1))
                .log(anyDouble(), any(UUID.class));
    }

}
