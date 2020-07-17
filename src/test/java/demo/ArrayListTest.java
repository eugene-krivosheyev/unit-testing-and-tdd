package demo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        // region Fixture / Arrange / Given
        final ArrayList sut = new ArrayList();
        final Object dummy = new Object();
        // endregion
        assumeTrue(sut.isEmpty()); // предположение, что массив пустой
        // region Act / When
        sut.add(dummy);
        // endregion

        // region Assert / Then
        assertEquals("", 1, sut.size()); // (строка, ожидаемое значение, фактическое значение)
        assertTrue(sut.contains(dummy)); // проверка что пустышка dummy содержится в листе
        // endregion
    }
}
