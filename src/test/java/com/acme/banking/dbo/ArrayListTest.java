package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.junit.Assume.assumeTrue;

import static org.hamcrest.CoreMatchers.is;

/**
 * TestCase for SUT
 */
public class ArrayListTest {

    @Test(timeout = 5_000, expected = NullPointerException.class) //BDD
    public void shouldSizeIncrementedWhenAddNullElement() {
        //AAA | GWT
        //region Given
        final ArrayList<Integer> sut = new ArrayList<>();
        //endregion

        //region When
        sut.add(null);
        //endregion

        //region Than
        Assert.assertEquals(1, sut.size());

        Assert.assertThat(sut.size(), is(1));
        assertThat(sut.size())
                .isEqualTo(1)
                .isNotNegative()
                .isGreaterThan(0);

        assertThat(sut)
                .contains(1)
                .containsExactly(1)
                .hasSize(1);
        //endregion
    }

    @Test
    public void shouldSizeIncrementedAndElementExistsWhenNotNullElementAdded() {
        //region Given | Fixture
        final ArrayList sut = new ArrayList();
        final Object dummy = new Object();
        //endregion
        //region Assumptions
        assumeTrue(!sut.isEmpty());
        //endregion

        //region When
        sut.add(dummy);
        //endregion

        //region Then
        Assert.assertEquals(1, sut.size());
        Assert.assertFalse(sut.isEmpty());
        //endregion
    }


    private int initialAmount;
    private int withdrawAmount;
    private boolean success;

    @Test
    public void shouldGetErrorWhenWithdrawMoreThanAmount() {
        final SavingAccount savingAccount = new SavingAccount(null, null, initialAmount);
        try {
            savingAccount.withdraw(withdrawAmount);
        } catch (WithdrawException e) {
            if (success) fail();
        }
    }
}
