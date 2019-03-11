package demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test
    public void shouldIncreaseSizeWhenAddElement() {
        //region Given
        ArrayList<Object> sut = new ArrayList<>();
        Object dummy = new Object();
        assumeTrue(sut.size() == 0);
        //endregion

        //region When
        sut.add(dummy);
        //endregion

        //region Then
        assertEquals(1, sut.size());
        //endregion
    }
}
