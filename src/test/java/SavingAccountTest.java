import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SavingAccountTest {
    final int id = 111;
    final double amount = 6.66;
    final Client client = new Client(111, "John Doe");
    SavingAccount sut = new SavingAccount(id, client, amount);

    @Test
    public void shouldThrowWhenIdNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-111, client, amount));
    }

    @Test
    public void shouldThrowWhenAmountNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, -6.66));
    }

    @Test
    public void shouldThrowWhenClientNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, null, amount));
    }

    @Test
    public void shouldCGetIdWhenSavingAccount() {
        assumeTrue(sut != null);
        Assertions.assertEquals(id, sut.getId());
    }

    @Test
    public void shouldGetAmountWhenSavingAccount() {
        assumeTrue(sut != null);
        Assertions.assertEquals(amount, sut.getAmount());
    }

    @Test
    public void shouldGetClientWhenSavingAccount() {
        assumeTrue(sut != null);
        Assertions.assertEquals(client, sut.getClient());
    }
}
