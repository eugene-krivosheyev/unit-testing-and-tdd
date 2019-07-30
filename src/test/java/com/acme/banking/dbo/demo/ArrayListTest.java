package com.acme.banking.dbo.demo;

import org.fest.assertions.api.Assertions;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;

public class ArrayListTest {
    @Test
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        assumeTrue(sut.isEmpty());

        sut.add(dummy);

        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy));
        // OR
        assertThat(sut)
                .contains(dummy)
                .hasSize(1);
    }

    @Test //State-based testing
    public void shouldUseElementsStringRepresentationWhenToString() {
        //region Given
        final ArrayList<Object> sut = new ArrayList<>();
        Object stub1 = mock(Object.class);
        Object stub2 = mock(Object.class);
        when(stub1.toString()).thenReturn("test value 1");
        when(stub2.toString()).thenReturn("test value 2");
        sut.add(stub1);
        sut.add(stub2);
        //endregion

        //region Then
        assertThat(sut.toString())
                .contains("test value 1")
                .contains("test value 2");
    }

    @Test //Interaction-based testing
    public void shouldCallElementsToStringWhenToString() {
        final ArrayList<Object> sut = new ArrayList<>();
        Object mock1 = mock(Object.class);
        Object mock2 = mock(Object.class);
        sut.add(mock1);
        sut.add(mock2);

        sut.toString();

        //TODO Captors
        verify(mock1).toString(); //any(MyClass.class));
        verify(mock2, times(1)).toString();
    }
}
