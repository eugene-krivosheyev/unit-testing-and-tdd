import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SavingAccountTest {
    final int VALID_ACC_ID = 1;
    final double VALID_ACC_AMOUNT = 1.1;
    final Client client = new Client(1, "John Doe");
    SavingAccount sut = new SavingAccount(VALID_ACC_ID, client, VALID_ACC_AMOUNT);

    @Test
    public void shouldNotCreateNewAccountWhenIdNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client, VALID_ACC_AMOUNT));
    }

    @Test
    public void shouldNotCreateNewAccountWhenAmountNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ACC_ID, client, -6.66));
    }

    @Test
    public void shouldNotCreateNewAccountWhenClientNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ACC_ID, null, VALID_ACC_AMOUNT));
    }

    @Test
    public void shouldCreateNewAccountSuccessfullyWhenArgumentsValid() {
        assumeTrue(sut != null);
        Assertions.assertEquals(VALID_ACC_ID, sut.getId());
        Assertions.assertEquals(VALID_ACC_AMOUNT, sut.getAmount());
        Assertions.assertEquals(client, sut.getClient());
    }

}
