package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;


public class SavingAccountTest {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");
    public static final Client CLIENT_STUB = new Client(ID_STUB,"stub test name");

    @Test(expected = IllegalArgumentException.class)
    public void shoudGetThrowWhenCreatedSavingAccountInNullArgumentUUID(){
         Account savingAccount = new SavingAccount(null, CLIENT_STUB, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoudGetThrowWhenCreatedSavingAccountInNullArgumentClient(){
        Account savingAccount = new SavingAccount(ID_STUB, null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoudGetThrowWhenCreatedSavingAccountAmountLessZero(){
        Account savingAccount = new SavingAccount(ID_STUB, CLIENT_STUB, -1);
    }

    @Test
    public void shoudGetThrowWhenCreatedSavingAccountAmountIsZero(){
        Account savingAccount = new SavingAccount(ID_STUB, CLIENT_STUB, 0);
    }

    @Test
    public void shoudReturnIdWhenUseGetId(){
        Account savingAccount = new SavingAccount(ID_STUB, CLIENT_STUB, 0);
//        assertThatThrownBy(() -> {
//            SavingAccount sut = new SavingAccount(null, CLIENT_STUB, AMOUNT_STUB);
//        }).isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("id must be not null");

    }

}
