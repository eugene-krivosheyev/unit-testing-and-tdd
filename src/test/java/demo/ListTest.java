package demo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ListTest {
    @Test
    //@Disabled //@Ignore
    public void shouldSizeIncrementedAndContainsItemWhenAddItem() {
        //region Arrange | Fixture | Given
        final List<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
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
                new TestParams(1, "", "name!")
        );
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
}
