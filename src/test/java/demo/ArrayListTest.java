package demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ArrayListTest {
    @Test //BDD = DDD(test)
    public void shouldSizeIncrementedWhenAddElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals("", 1, sut.size());
        assertFalse("", sut.isEmpty());
        //endregion
    }
}
