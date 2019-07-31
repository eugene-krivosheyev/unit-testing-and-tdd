package com.acme.banking.dbo;

import com.acme.banking.dbo.service.Reporting;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class ReportingTest {
    @Test
    public void shouldMakeMarkupReportForAccountWhenAlreadyExists() {
        final Reporting sut = new Reporting();

        assertThat(sut.getReportForAccount(UUID.fromString("0")))
                .contains("## *" + UUID.fromString("0") + "* $100.");
    }

    @Test
    public void shouldMakeMarkupReportForClientWhenAlreadyExists() {
        final Reporting sut = new Reporting();

        assertThat(sut.getReportForClient(UUID.fromString("0")))
                .contains("# " + *id* + name)
                .contains("## " + *id1* + $100);
                .contains("## " + *id2* + $100);
    }
}
