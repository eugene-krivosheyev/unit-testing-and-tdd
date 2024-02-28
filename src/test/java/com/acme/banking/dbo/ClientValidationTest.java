package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.exception.IdValidationException;
import com.acme.banking.dbo.exception.NameAlphabeticalValidationException;
import com.acme.banking.dbo.exception.NameValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientValidationTest {


    @DisplayName("Success create client with valid data")
    @ParameterizedTest
    @CsvSource(value =
        {
            "0, АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНн - ОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя",
            "2147483647, ABCDEFGHIJKLMNOPQRSTUVWXYZ - abcdefghijklmnopqrstuvwxyz"
        }
    )
    public void successCreateClientWithValidData(int id, String name) {

        Executable actSut = () -> new Client(id, name);

        assertDoesNotThrow(actSut);
    }

    @DisplayName("Невалидные данные: в Name посторонние символы")
    @ParameterizedTest
    @CsvSource(value = {
        "0, 22",
        "0, /",
        "0, £"
    })
    public void failedCreateClientWithNameHaveNotLettersAndSpaceAndHyphen(int id, String name) {
        Executable actSut = () -> new Client(id, name);

        assertThrows(NameAlphabeticalValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: отрицательный id")
    public void failedCreateClientWithNegativeId() {
        int id = -1;
        String name = "Максим";

        Executable actSut = () -> new Client(id, name);

        assertThrows(IdValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name - null")
    public void failedCreateClientWithNameIsNull() {
        int id = 0;
        String name = null;

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: пустое Name")
    public void failedCreateClientWithNameIsEmpty() {
        int id = 0;
        String name = "";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name - только пробел")
    public void failedCreateClientWithNameIsOnlySpace() {
        int id = 0;
        String name = " ";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name - только дефис")
    public void failedCreateClientWithNameIsOnlyHyphen() {
        int id = 0;
        String name = "-";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name начинается с пробела")
    public void failedCreateClientWithNameStartsWithSpace() {
        int id = 0;
        String name = " Максим";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name оканчивается пробелом")
    public void failedCreateClientWithNameEndsWithSpace() {
        int id = 0;
        String name = "Максим ";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name начинается с дефиса")
    public void failedCreateClientWithNameStartsWithHyphen() {
        int id = 0;
        String name = "-Максим";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: Name оканчивается дефисом")
    public void failedCreateClientWithNameEndsWithHyphen() {
        int id = 0;
        String name = "Максим-";

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut);
    }
}
