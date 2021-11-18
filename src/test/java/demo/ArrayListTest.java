package demo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;;

/**
 * SUT
 * Test class, Test case, [Test suite]
 */
public class ArrayListTest {
    /**
     * Test, Test scenario, Test method
     * Naming: BDD
     */
    @Test //@Disabled
    public void shouldStoreWhenAddElement() {
        //region 1: Fixture | Arrange | Given
        final Collection<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        assumeTrue(sut.isEmpty());
        //endregion

        //region 2: Act | When
        sut.add(dummy);
        //endregion

        //region 3: Assert | Then
        //logic domain assertion -> assertions
//        assertTrue(sut.contains(dummy));
//        assertEquals(1, sut.size());

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(1, null)
        );
        assertTrue(thrown.getMessage().contains("!!"));
        //endregion
    }
    //get
}

