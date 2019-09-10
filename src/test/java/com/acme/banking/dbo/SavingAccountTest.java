package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


import java.util.UUID;

public class SavingAccountTest {
	@Rule
	public final TestRule globalTimeout = Timeout.seconds(5);

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldSavePropertiesWhenCreated(){
		UUID systemAccountId = UUID.randomUUID();
		Client someClient = new Client(UUID.randomUUID(), "Random client name");
		double amount = 0.1d;

		SavingAccount sut = new SavingAccount(systemAccountId, someClient, amount);

		assertThat(
				sut.getAmount(), allOf(
						equalTo(amount),
						notNullValue()
				)
		);
	}

	@Test
	public void shouldThrowExceptionWhenCreatedWithWrongAmount(){
		thrown.expect(IllegalArgumentException.class);
		Client client = new Client(UUID.randomUUID(), "Some name");

		SavingAccount sut = new SavingAccount(UUID.randomUUID(), client, 0.0d);
	}

	@Test
	public void shouldThrowExceptionWhenCreatedWithClientNameOnlyWithOneCharacter(){
		thrown.expect(IllegalArgumentException.class);
		Client client = new Client(UUID.randomUUID(), "b");

		SavingAccount sut = new SavingAccount(UUID.randomUUID(), client, 111d);
	}
}
