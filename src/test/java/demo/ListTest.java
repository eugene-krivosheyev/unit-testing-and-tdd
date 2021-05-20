package demo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ListTest {
    @Test //BDD
    //@Disabled //@Ignore
    public void shouldIncrementSizeAndItemExistsWhenAddItem() {
        //region Arrange | Fixture | Given
        final ArrayList sut = new ArrayList();
        final Object dummy = new Object();
        assumeTrue(sut.size() == 0); //assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //if (true) throw new RuntimeException("!!!");
        //endregion

        //region Assert | Then
        assertEquals(1, sut.size()); //assertFalse(sut.isEmpty());
        assertTrue(sut.contains(dummy)); //assertEquals(dummy, sut.get(0));

        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(-1, "dummy name"),
                "id!"
        );

        //endregion
    }

    @Test
    public void shouldPass() {
        System.getProperties().forEach((k,v) -> System.out.println(k + " : " + v));
    }
}
