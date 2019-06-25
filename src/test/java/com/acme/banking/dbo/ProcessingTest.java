package com.acme.banking.dbo;

import com.acme.banking.dbo.service.Processing;
import com.acme.banking.dbo.service.TxLog;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private static final double ANY_AMOUNT = 1.0;
    private static final UUID ANY_UUID = UUID.randomUUID();

    @Test
    public void shouldLogTxWithTxLogWhenCacheOperation() {
        TxLog mockTxLog = mock(TxLog.class);
        Processing sut = new Processing(mockTxLog);

        sut.cash(ANY_AMOUNT, ANY_UUID);

        verify(mockTxLog, times(1))
                .log(anyDouble(), any(UUID.class));
    }
}
