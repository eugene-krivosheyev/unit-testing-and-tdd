package demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

/**
 * Test Case
 */
public class ArrayListTests {
    @Test
    public void shouldSizeIncrementedAndElementContainedWhenAddElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertFalse(sut.isEmpty());
        assertEquals(1, sut.size());
//
//        assertSame(dummy, sut.get(0));
        assertTrue(sut.contains(dummy));
        //endregion
    }


}
