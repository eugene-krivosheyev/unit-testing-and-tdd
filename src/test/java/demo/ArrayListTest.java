package demo;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;

public class ArrayListTest {
    @Test //BDD
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        //region Fixture | Arrange | Given
        ArrayList sut = new ArrayList();
        Object dummy = new Object();
        //endregion
        assumeTrue(sut.isEmpty());

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals("", 1, sut.size());
        assertTrue(sut.contains(dummy));
        //endregion
    }

    @Test
    public void shouldUseElementsStringRepresentationWhenToString1() {
        final ArrayList<Object> sut = new ArrayList<>();
        Object elementStub = mock(Object.class);
        when(elementStub.toString()).thenReturn("test string 1");

        sut.add(elementStub);
        System.out.println(elementStub.toString());

        assertThat(sut.toString())
            .contains("test string 1");
    }

    @Test @Ignore
    public void shouldUseElementsStringRepresentationWhenToString2() {
        final ArrayList sut = new ArrayList();
        Object elementMock = mock(Object.class);
        sut.add(elementMock);

        sut.toString();

        verify(elementMock, times(1)).toString();
//        verify(emailServiceMock).send(anyString());
    }
}
