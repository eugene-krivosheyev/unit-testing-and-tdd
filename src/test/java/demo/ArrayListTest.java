package demo;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    private ArrayList<Object> sut;

    @Test //BDD = DDD(test)
    public void shouldGetElementAtTailAndSizeIncrementedWhenAddNullElement() {
        //region Fixture | Arrange | Given
        sut = new ArrayList<>();
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

        assertThat(sut, allOf(hasItem(dummy)));
        //endregion
    }

    @Test
    public void shouldSuccess() {

    }
}
