package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * SUT: subject | system under test
 *
 * TestCase | Test class | Test Fixture
 */
public class HashSetTest {
    /**
     * Test | Test method
     * BDD
     */
    @Test
    public void shouldAddElementWhenElementIsNull() {
        // region Arrange | Given | Fixture
        HashSet<Object> sut = new HashSet<>();
        assumeTrue(sut.size() == 0);
        // endregion

        // region Act | When
        sut.add(null);
        // endregion

        // region Assert | Then
        Assertions.assertAll("combine asserts",
                () -> Assertions.assertEquals(1, sut.size()),
                () -> Assertions.assertTrue(sut.contains(null))
        );
        // endregion
    }

    @Test
    public void shouldAddElementWhenElementIsNull1 () {

    }

    @Test @DisplayName("shouldNot AddElement When ElementIsDuplicated")
    public void shouldNotAddElementWhenElementIsDuplicated() {
        //
    }
}
