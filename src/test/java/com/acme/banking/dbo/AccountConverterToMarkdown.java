package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountToMarkdownConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountConverterToMarkdown {

    @Test
    void givenAccountShouldConvertToAccount() {
        var converter = new AccountToMarkdownConverter();
        var mockedAccount = mock(SavingAccount.class);
        when(mockedAccount.getAmount()).thenReturn(100d);

        assertEquals("Account balance: 100.0", converter.toMarkdown(mockedAccount));
    }
}
