package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Processing - account test ")
class ProcessingAccountUnitTest {

    @InjectMocks
    Processing sut;

    @Mock
    AccountRepository accountRepository;

    @Test
    void shouldReturnAccountsWhenClientIdIsEqual1() {

        Account dummyAccount = mock(Account.class);
        when(accountRepository.findAllByClientId(1)).thenReturn(of(dummyAccount));

        Collection<Account> accounts = sut.getAccountsByClientId(1);

        assertThat(accounts)
                .describedAs("Account collection should contains exactly one dummy account")
                .containsExactly(dummyAccount);

    }

}