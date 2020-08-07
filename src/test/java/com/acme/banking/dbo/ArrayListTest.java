package com.acme.banking.dbo;

import org.junit.*;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ArrayListTest {
    private ArrayList<Object> sut;

    @BeforeClass @AfterClass
    public static void globalSetup() {

    }

    @Before @After
    public void setUp() {
        sut = new ArrayList<>();
    }

    @Test
    public void shouldUseElementsStringRepresentationWhenToString() {
        final ArrayList<Object> sut = new ArrayList<>();
        Object stubElement = mock(Object.class);
        //dummy пустыщка
        // stub заглушка
        when(stubElement.toString()).thenReturn("element");
        sut.add(stubElement);

        //fack -- очень похож
        assertThat(sut.toString()).contains("element");


        //dummy stub fake mock spy
    }

    @Test
    //interaction-based testing
    public void shouldCallElementsToStringWhenToString() {
        //mock - объект стукач - правтльно ли наш объект передавал данные
        final ArrayList<Object> sut = new ArrayList<>();
        final Object mock = mock(Object.class);
        sut.add(mock);

        sut.toString();

        verify(mock,times(1)).toString();

    }

}
