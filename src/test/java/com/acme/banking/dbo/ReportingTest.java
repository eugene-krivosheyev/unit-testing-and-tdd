package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.AccountRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTest {
    private AccountRepository accountRepository;
    private AbstractReportingService sut;

    @Before
    public void setUp() {
        accountRepository = mock(AccountRepository.class);
        sut = new ReportingService(accountRepository);
    }

    @Test
    public void shouldGetEmptyReportForAccountWhenNoAccount() {
        when(accountRepository.findAccountById(0))
                .thenThrow(new NoSuchElementException());

        final AbstractReportingService sut = new ReportingService(accountRepository);

        assertThat(sut.getReportForAccount(0))
                .contains("## <No Account for id " + 0);
    }



    @Test
    public void shouldGetReportForAccountWhenItExists() {
        Account stubAccount = mock(Account.class);
        when(stubAccount.getId()).thenReturn(0);
        when(stubAccount.getAmount()).thenReturn(200.);

        when(accountRepository.findAccountById(0))
                .thenReturn(stubAccount);

        assertThat(sut.getReportForAccount(0L))
                .isEqualTo("## *0* 200.0");
    }
}
