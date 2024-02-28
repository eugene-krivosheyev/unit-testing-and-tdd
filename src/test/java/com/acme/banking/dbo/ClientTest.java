package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class ClientTest {
    @Test @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
                allOf(
                        hasProperty("id", notNullValue()),
                        hasProperty("id", equalTo(clientId)),
                        hasProperty("name", is(clientName))
                ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @DisplayName("Валидные данные")
    @ParameterizedTest
    @CsvSource(value = {
            "0, АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНн - ОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя",
            "2147483647, ABCDEFGHIJKLMNOPQRSTUVWXYZ - abcdefghijklmnopqrstuvwxyz"})
    public void successCreateClientWithValidData(int id, String name) {
        new Client(id, name);
    }

    @DisplayName("Невалидные данные: в Name посторонние символы")
    @ParameterizedTest
    @CsvSource(value = {
            "0, 22",
            "0, /",
            "0, £"})
    public void failedCreateClientWithNameHaveNotLettersAndSpaceAndHyphen(int id, String name) {
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name содержит символы помимо кириллицы, латиницы, пробела и дефиса", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: отрицательный id")
    public void failedCreateClientWithNegativeId() {
        int id = -1;
        String name = "Максим";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("ID не может быть отрицательным числом", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name - null")
    public void failedCreateClientWithNameIsNull() {
        int id = 0;
        String name = null;
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name не должно быть null", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: пустое Name")
    public void failedCreateClientWithNameIsEmpty() {
        int id = 0;
        String name = "";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name должно содержать хотя бы 1 букву", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name - только пробел")
    public void failedCreateClientWithNameIsOnlySpace() {
        int id = 0;
        String name = " ";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name должно содержать хотя бы 1 букву", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name - только дефис")
    public void failedCreateClientWithNameIsOnlyHyphen() {
        int id = 0;
        String name = "-";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name должно содержать хотя бы 1 букву", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name начинается с пробела")
    public void failedCreateClientWithNameStartsWithSpace() {
        int id = 0;
        String name = " Максим";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name не должно начинаться с пробела", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name оканчивается пробелом")
    public void failedCreateClientWithNameEndsWithSpace() {
        int id = 0;
        String name = "Максим ";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name не должно оканчиваться пробелом", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name начинается с дефиса")
    public void failedCreateClientWithNameStartsWithHyphen() {
        int id = 0;
        String name = "-Максим";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name не должно начинаться с дефиса", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }

    @Test
    @DisplayName("Невалидные данные: Name оканчивается дефисом")
    public void failedCreateClientWithNameEndsWithHyphen() {
        int id = 0;
        String name = "Максим-";
        try {
            new Client(id, name);
        } catch (InternalError e) {
            assertEquals("Name не должно оканчиваться дефисом", e.getMessage(),
                    "Исключения не совпадают!");
        }
    }
}
