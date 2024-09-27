package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.exception.IdValidationException;
import com.acme.banking.dbo.exception.NameAlphabeticalValidationException;
import com.acme.banking.dbo.exception.NameValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientValidationTest {


    @DisplayName("Success create client with valid data")
    @ParameterizedTest
    @CsvSource(value =
        {
            "0, АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНн - ОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя",
            "2147483647, ABCDEFGHIJKLMNOPQRSTUVWXYZ - abcdefghijklmnopqrstuvwxyz"
        }
    )
    void successCreateClientWithValidData(int id, String name) {

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
    void failedCreateClientWithNameHaveNotLettersAndSpaceAndHyphen(int id, String name) {
        Executable actSut = () -> new Client(id, name);

        assertThrows(NameAlphabeticalValidationException.class, actSut);
    }

    @Test
    @DisplayName("Невалидные данные: отрицательный id")
    void failedCreateClientWithNegativeId() {
        int id = -1;
        String name = "Максим";

        Executable actSut = () -> new Client(id, name);

        assertThrows(IdValidationException.class, actSut);
    }



    @DisplayName("Success create client with valid data")
    @ParameterizedTest
    @MethodSource("provideValidationIncorrectDataForClientCreation")
    void givenClientCreationShouldRaiseErrorsWhileValidation(int id, String name, String description) {

        Executable actSut = () -> new Client(id, name);

        assertThrows(NameValidationException.class, actSut, description);
    }

    private static Stream<Arguments> provideValidationIncorrectDataForClientCreation(){
        return Stream.of(
            Arguments.of(0, "Максим-", "Невалидные данные: Name оканчивается дефисом"),
            Arguments.of(0, "-Максим", "Невалидные данные: Name начинается с дефиса"),
            Arguments.of(0, "Максим ", "Невалидные данные: Name оканчивается пробелом"),
            Arguments.of(0, " Максим", "Невалидные данные: Name начинается с пробела"),
            Arguments.of(0, "-", "Невалидные данные: Name - только дефис"),
            Arguments.of(0, "", "Невалидные данные: пустое Name"),
            Arguments.of(0, null, "Невалидные данные: Name - null")
        );
    }
}
