package demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test //BDD
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        //region Fixture | Arrange | Given
        ArrayList sut = new ArrayList();
        Object dummy = new Object();
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
