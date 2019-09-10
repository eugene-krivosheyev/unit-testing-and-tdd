package demo;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

/**
 * Test-Case
 */
public class ArrayListTest {
    private ArrayList<Object> sut;
    @Rule public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpGlobal() {

    }

    @Before
    public void setUp() {
        sut = new ArrayList<>();
    }

    @After
    public void tearDown() {

    }

    @Test (timeout = 10_000) //BDD = DDD к тестам
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        //region Fixture | Arrange | Given
        final Object dummy = new Object();
        assumeTrue(sut.size() == 0);
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy));
        //endregion
    }


    @Test
    public void shouldSaveWhenAddNull() {
        final ArrayList<Object> sut = new ArrayList<>();
        sut.add(null);
        assertEquals(1, sut.size());
        assertTrue(sut.contains(null));

        Assertions.assertThat("")
                .contains("")
                .isEmpty();
    }

}
