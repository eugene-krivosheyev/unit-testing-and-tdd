package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


import java.util.UUID;

public class SavingAccountTest {
	@Test
	public void shouldSavePropertiesWhenCreated(){
		//region given
		UUID systemAccountId = UUID.randomUUID();
		Client someClient = new Client(UUID.randomUUID(), "Random client name");
		double amount = 0.1d;
		//

		//when
		SavingAccount sut = new SavingAccount(systemAccountId, someClient, amount);
		//

		//assert
		assertThat(
				sut.getAmount(), allOf(
						equalTo(amount),
						notNullValue()
				)
		);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenCreatedWithWrongAmount(){
		Client client = new Client(UUID.randomUUID(), "Some name");

		SavingAccount sut = new SavingAccount(UUID.randomUUID(), client, 0.0d);
	}
}
