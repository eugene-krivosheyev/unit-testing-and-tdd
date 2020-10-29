package demo;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test Case
 */
public class ArrayListTests {
    @Test(timeout = 10_000)
    public void shouldSizeIncrementedAndElementContainedWhenAddNullElement() {
        //region Fixture | Arrange | Given
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = null; //mock()
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

    @Test(expected = RuntimeException.class)
    public void shouldUseElementsStringRepresentationsWhenToString() {
        Object objectStub = mock(Object.class);
        when(objectStub.toString())
                .thenReturn("stub string")
                .thenReturn("1")
                .thenThrow(new RuntimeException("ex"));

        final ArrayList<Object> sut = new ArrayList<>();
        sut.add(objectStub);

        final String stringRepresentation = sut.toString();

        assertTrue(stringRepresentation.contains("stub string"));

        System.out.println(objectStub.toString());
        System.out.println(objectStub.toString());
    }
}
