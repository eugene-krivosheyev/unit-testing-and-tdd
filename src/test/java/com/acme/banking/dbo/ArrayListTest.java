package com.acme.banking.dbo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * TestCase for SUT
 */
public class ArrayListTest {
    @Test //BDD
    public void shouldSizeIncrementedWhenAddNullElement() {
        //AAA | GWT
        //region Given
        final ArrayList sut = new ArrayList();
        //endregion

        //region When
        sut.add(null);
        //endregion

        //region Than
        assertEquals(1, sut.size());
//        assertEquals(.3, .1 + .2, 0.000000000000000000000001);
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
        assertEquals(1, sut.size());
        assertFalse(sut.isEmpty());
        //endregion
    }
}
