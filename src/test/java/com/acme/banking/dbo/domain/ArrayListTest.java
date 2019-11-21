package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.SavingAccount;
import com.sun.deploy.security.ruleset.ExceptionRule;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.junit.Assume.assumeTrue;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 * TestCase for SUT
 */
public class ArrayListTest {
    private ExpectedException expected = ExpectedException.none();
    private ArrayList<Object> sut;
    private Object stub1;
    private Object stub2;

    @BeforeClass
    public static void setUpGlobals() {

    }

    @AfterClass
    public static void clearGlobals() {

    }

    @Before
    public void setUp() {
        stub1 = mock(Object.class);
        stub2 = mock(Object.class);
        sut = new ArrayList<>();
    }

    @After
    public void clear() {

    }


    @Test(timeout = 5_000, expected = NullPointerException.class) //BDD
    public void shouldSizeIncrementedWhenAddNullElement() {
        //AAA | GWT
        //region Given
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

    @Test
    public void shouldCallElementsToStringWhenToStringStateBased() {
        when(stub1.toString()).thenReturn("1");
        when(stub2.toString()).thenReturn("2");

        sut.add(stub1);
        sut.add(stub2);

        assertThat(sut.toString())
                .contains("1")
                .contains("2");
    }



    @Test
    public void shouldCallElementsToStringWhenToStringInteractionBased() {
        sut.add(stub1);
        sut.add(stub2);

        sut.toString();

        verify(stub1, times(1)).toString();
        verify(stub2, times(1)).toString();
    }
}
