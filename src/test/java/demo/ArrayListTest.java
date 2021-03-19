package demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
 * TestCase for SUT | Fixture
 * Test -> documentation
 */
public class ArrayListTest {
    /**
     * BDD = DDD(tests)
     */
    @Test // -> config (xml, json)
    //@Ignore | @Disable
    //Multiple Spec by Example
    public void shouldSizeIncrementedAndContainsElementWhenAddElement() {
        //region Arrange | Given | Fixture
        final Object dummy = new Object();
        final ArrayList<Object> sut = new ArrayList<>();
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //if (1==1) throw new RuntimeException("aaaaa!");
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size()); // -> AssertionError
        assertTrue(sut.contains(dummy));
        //endregion
    }
}
