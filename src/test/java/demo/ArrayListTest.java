package demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


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

    @Test
    public void shouldUseElementsStringRepresentationWhenToString() {
        //region given
        final ArrayList<Object> sut = new ArrayList<>();
        Object stub = mock(Object.class); // spy()
        when(stub.toString()).thenReturn("test string"); //State-based testing | Classicist
//        when(stub.equals("abc")).thenReturn(true);
        sut.add(stub);
        //endregion

        //region when
        final String sutToString = sut.toString();
        //endregion

        //region then
        //assertTrue(stub.equals("abc"));
        //assertFalse(stub.equals("not abc"));
        assertTrue(sutToString.contains("test string"));
        //endregion
    }

    @Test
    @Disabled
    public void shouldCallsElementsStringRepresentationWhenToString() { //Interaction-based testing | Mockist
        final ArrayList<Object> sut = new ArrayList<>();
        final Object mock = mock(Object.class);
        sut.add(mock);

        sut.toString();

        verify(mock, times(1)).toString(); //any(MyParam.class)
    }

    @Test
    @Disabled
    public void spyDemo() {
        final Object spy = spy(new Object());
        when(spy.hashCode()).thenReturn(1_000);

        verify(spy, atLeastOnce()).equals("exact string");
    }

    @Test
    @Disabled
    public void stubExceptionDemo() {
        final Object stub = mock(Object.class);

        when(stub.toString()).thenThrow(new IllegalArgumentException("arg"));
        doThrow(new IllegalArgumentException("arg")).when(stub).toString();
    }
}
