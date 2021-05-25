package demo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    //@Disabled //@Ignore
    public void shouldSizeIncrementedAndContainsItemWhenAddItem() {
        //region Arrange | Fixture | Given
        final List<Object> sut = new ArrayList<>();
        final Object dummy = mock(Object.class);
        assumeTrue(sut.size() == 0); //assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //if (true) throw new RuntimeException();
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy)); //assertEquals(dummy, sut.get(0));
        //endregion
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    public void demoParamsTest(TestParams testParams) {
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Client(testParams.getId(), testParams.getName()));
        assertEquals(testParams.getExceptionMessage(), thrown.getMessage());
    }

    static Stream<TestParams> paramsProvider() {
        return Stream.of(
                new TestParams(-1, "dummy name", "id!"),
                new TestParams(1, null, "name!"),
                new TestParams(1, "", "name!!")
        );
    }

    @Test
    public void shouldUseItemsStringRepresentationWhenToString() {
        Object stubItem1 = mock(Object.class);
        when(stubItem1.toString())
                .thenReturn("str10")
                .thenReturn("str11")
                .thenReturn("str12");

        Object stubItem2 = mock(Object.class);
        when(stubItem2.toString()).thenReturn("str2");

        final ArrayList<Object> sut = new ArrayList<>();
        sut.add(stubItem1);
        sut.add(stubItem2);

        assertThat(sut.toString())
                .contains("str1")
                .contains("str2");

    }
}

class TestParams {
    private int id;
    private String name;
    private String exceptionMessage;

    public TestParams(int id, String name, String exceptionMessage) {
        this.id = id;
        this.name = name;
        this.exceptionMessage = exceptionMessage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public String toString() {
        return "case for: " + id + ", " + name + ". Waiting for message: " + exceptionMessage;
    }
}