package demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * Test-Case
 */
public class ArrayListTest {
    @Test //BDD = DDD к тестам
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        assumeTrue(sut.size() == 0);
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy));
        //endregion
    }

}
