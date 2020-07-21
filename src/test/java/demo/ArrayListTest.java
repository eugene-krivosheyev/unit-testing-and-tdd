package demo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayListTest {
    private ArrayList sut;
    //@Ignore
    @Test(timeout = 10)
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() { //shouldOUTCOMEWhenINCOME
        //region Fixture | Arrange | Given
        sut = new ArrayList();
        final Object dummy = new Object();
        //endregion

        //region Assume
        //assumeTrue(sut.size() == 1);
        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals("Size incremented",1,sut.size());
        assertTrue("Object contained", sut.contains(dummy));
        //endregion
    }

    @Test(expected = Exception.class)
    public void shouldGetErrorWhenAddNullElement() {
            sut.add(null);
    }

    @Test
    public void shouldUseElementsStringRepresentationWhenToString1() {
        final ArrayList<Object> sut = new ArrayList<>();
        Object elementStub = mock(Object.class);

    }

    @Test
    public void shouldUseElementsStringRepresentationWhenToString2() {
        final ArrayList sut = new ArrayList();
        Object elementMock = mock(Object.class);
    }

    public void smth() {
        System.out.println("smth");
    }
}
