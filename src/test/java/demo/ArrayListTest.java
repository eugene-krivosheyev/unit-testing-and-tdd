package demo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test //BDD = DDD(test)
    public void shouldGetElementAtTailAndSizeIncrementedWhenAddNullElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = null;
        //endregion
        //region Assumptions check
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals("Not so good assert: got assumes", dummy, sut.get(sut.size() - 1));
        assertTrue(sut.contains(dummy));
        assertFalse(sut.isEmpty());
        //endregion
    }
}
