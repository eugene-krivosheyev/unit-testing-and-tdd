package demo;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

/**
 * Test Case
 */
public class ArrayListTests {
    @Test(timeout = 10_000)
    public void shouldSizeIncrementedAndElementContainedWhenAddNullElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = null;
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertFalse(sut.isEmpty());
        assertEquals(1, sut.size());
//
        assertTrue(sut.contains(dummy));
        assertSame(dummy, sut.get(0));

        assertThat(sut,
                allOf(
                    hasItems(dummy),
                    notNullValue()
            ));

        Assertions.assertThat(sut)
                .hasSize(1)
                .containsExactly(dummy)
                .isNotNull();

        Assertions.assertThat("abc").isNotNull().contains("a").containsIgnoringCase("C");
        //endregion
    }
}
