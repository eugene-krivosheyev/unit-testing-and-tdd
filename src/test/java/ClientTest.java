import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ClientTest {
    final int id = 111;
    final String name = "John Doe";
    Client sut = new Client(id, name);
    @Test
    public void shouldThrowWhenIdNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(-111, name));
    }

    @Test
    public void shouldThrowWhenNameEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Client(id, ""));
    }

    @Test
    public void shouldCGetIdWhenCreateClient() {
        assumeTrue(sut != null);
        Assertions.assertEquals(id, sut.getId());
    }

    @Test
    public void shouldGetNameWhenCreateClient() {
        assumeTrue(sut != null);
        Assertions.assertEquals(name, sut.getName());
    }
}
