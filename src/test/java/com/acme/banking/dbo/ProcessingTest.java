package com.acme.banking.dbo;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

//Fixture
public class ProcessingTest {
    private AccountRepository accountRepoStub;
    private Account accountStub;
    private Processing sut;

    @BeforeAll // @AfterAll
    public static void globalSetUp() {

    }

    @BeforeEach // @AfterEach
    public void setUp() {
        accountRepoStub = mock(AccountRepository.class);
        accountStub = mock(Account.class);
        sut = new Processing(accountRepoStub, mock(Cash.class));
    }

    @Test
    public void shouldGetStoredAccountWhenGetExistedAccountById() {
        accountStub = mock(Account.class);

        when(accountRepoStub.getAccountsByClientId(any(Integer.class)))
                .thenReturn(Arrays.asList(accountStub))
                .thenReturn(Collections.EMPTY_SET)
                .thenThrow(new IllegalStateException("NO ENTITY!"));

        assertThat(sut.getAccountsByClientId(1)).containsExactly(accountStub);
    }

    @Test
    public void shouldGetErrorAccountWhenGetNotExistedAccountById() {
        when(accountRepoStub.getAccountsByClientId(anyInt())).thenThrow(new IllegalStateException("!!!!!"));

        assertThrows(
                IllegalStateException.class,
                () -> sut.getAccountsByClientId(2)
        );
    }

    @Test
    public void shouldTransferWhenValidAmount() {
        Account accountFromSpy = spy(new SavingAccount(1, null, 0));
        Account accountToStub = mock(Account.class);
        when(accountFromSpy.getAmount()).thenReturn(10.);
        when(accountToStub.getAmount()).thenReturn(2.);
        when(accountRepoStub.getAccountById(1)).thenReturn(accountFromSpy);
        when(accountRepoStub.getAccountById(2)).thenReturn(accountToStub);

//        MockAccountRepositoryBuilder
//        DbAccountRepositoryBuilder
//                .withAccount()
//                .withAccount(1, null, 0)
//                .withAccount(2, 100)
//                        .withClient("client1")
//            .build();


        sut.transfer(1, 2, 9);

        verify(accountRepoStub, times(1)).getAccountById(1);
        verify(accountRepoStub, atLeastOnce()).getAccountById(2); //anyInt()
        verify(accountFromSpy).setAmount(1);
        verify(accountToStub).setAmount(11);
        verify(accountRepoStub).save(accountFromSpy);
        verify(accountRepoStub).save(accountToStub);
    }

    class Nested {
        //fixture
        //tests
    }
}
