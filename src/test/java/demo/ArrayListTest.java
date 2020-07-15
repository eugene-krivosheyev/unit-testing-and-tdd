package demo;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {

    @Ignore
    @Test(timeout = 10_000) //BDD
    public void shouldXSizeIncrementedAndElementContainedWhenElementAdded() {
        //region Fixture | Arrange | Given
        final ArrayList sut = new ArrayList();
        final Object dummy = new Object();
        //endregion
        assumeTrue(sut.isEmpty());

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals("", 1, sut.size());
        assertTrue(sut.contains(dummy));
        //endregion

    }
}