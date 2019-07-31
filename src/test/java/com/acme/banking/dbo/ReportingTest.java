package com.acme.banking.dbo;

import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.reflect.core.Reflection.field;

public class ReportingTest {
    @Test
    public void shouldMakeMarkupReportForAccountWhenAlreadyExists() {
        final Reporting sut = new Reporting();

        //https://github.com/alexruiz/fest-reflect
        assertThat(
                field("state").ofType(Integer.class).in(sut).get())
            .isEqualTo(1);
    }
}
