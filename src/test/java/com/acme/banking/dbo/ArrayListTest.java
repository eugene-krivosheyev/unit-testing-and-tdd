package com.acme.banking.dbo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

//Test Case
public class ArrayListTest {
    @Test
    public void shouldBeEmptyWhenCreated() {
        ArrayList<Object> sut = new ArrayList<>();
        assertEquals(0, sut.size());
    }

    @Test //= scenario
    public void shouldSizeIncrementedAndAddedElementExistsWhenAddElement() {
        //region Fixture | Arrange | Given
        ArrayList<Object> sut = new ArrayList<>();
        Object dummy = new Object();
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size());
        assertSame(dummy, sut.get(0));
        //endregion
    }
}
