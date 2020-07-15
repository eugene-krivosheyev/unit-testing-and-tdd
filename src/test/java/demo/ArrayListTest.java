package demo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

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

    public void smth() {
        System.out.println("smth");
    }
}
