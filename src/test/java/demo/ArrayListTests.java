package demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test Case
 */
public class ArrayListTests {
    @Test
    public void shouldSizeIncrementedAndElementContainedWhenAddElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
//        assertFalse("", sut.isEmpty());
//        assertEquals(2, sut.size());
//
//        assertSame(dummy, sut.get(0));
        assertTrue(sut.contains(dummy));
        //endregion
    }
}
