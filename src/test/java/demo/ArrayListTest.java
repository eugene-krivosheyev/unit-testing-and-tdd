package demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * SUT
 * Test class, Test case, [Test suite]
 */
public class ArrayListTest {
    /**
     * Test, Test scenario, Test method
     * Naming: BDD
     */
    @Test
    public void shouldStoreWhenAddElement() {
        //region 1: Fixture | Arrange | Given
        final Object dummy = new Object();
        final Collection<Object> sut = new ArrayList<>();
        //endregion

        //region 2: Act | When
        //endregion

        //region 3: Assert | Then
        //logic domain assertion -> assertions
        //endregion
    }

    //get
}
