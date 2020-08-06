package demo;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;

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

        //https://stackoverflow.com/questions/57476351/junit5-how-to-assert-several-properties-of-an-object-with-a-single-assert-call
        assertThat(sut)
                .hasSize(1)
                .isNotEmpty()
                .containsExactly(dummy);
        //endregion
    }

    @Test
    public void shouldUseElementsStringRepresentationWhenToString() {
        final ArrayList<Object> sut = new ArrayList<>();
        Object stubElement = mock(Object.class);
        when(stubElement.toString()).thenReturn("element string representation");
        sut.add(stubElement);

        assertThat(sut.toString()).contains("element string representation");
    }

    @Test
    public void shouldCallElementsToStringWhenToString() {
        final ArrayList<Object> sut = new ArrayList<>();
        final Object mock = mock(Object.class);
        sut.add(mock);

        sut.toString();

        verify(mock, times(1)).toString(); //any()
    }
}
