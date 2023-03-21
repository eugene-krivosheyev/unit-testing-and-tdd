package com.acme.banking.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class ReportingTest {
    @Mock
    private Branch branch;
    @Mock
    private Client client;

    @Test
    public void shouldFullReportWhenValidParam() {
        //given
        Reporting sut = new Reporting();
        String expectedReport = """
                Branch # 1
                        ==========
                        Client # 1: Name1
                        ----------
                        - Account # 1: 100.0
                        - Account # 2: 200.0

                        Client # 2: Name2
                        ----------
                        - Account # 1: 100.0
                        - Account # 2: 200.0

                            Branch # 2
                            ==========
                            Client # 3: Name3
                            ----------
                            - Account # 1: 100.0
                            - Account # 2: 200.0

                            Client # 4: Name3
                            ----------
                            (no accounts)
                 """;

        //when
        String report = sut.getReport(branch);

        //then
        assertEquals(expectedReport, report);
    }

    @Test
    public void shouldReportWhenClientHasOneAccount() {
        //given
        Reporting sut = new Reporting();
        String expectedReport = """
                      Client # 1: Name1
                           ----------
                           - Account # 1: 100.0
                """;
        //when
        String report = sut.getReport(client);

        //then
        assertEquals(expectedReport, report);
    }

    @Test
    public void shouldReportWhenClentHasNotAccount() {
        //given
        Reporting sut = new Reporting();
        String expectedReport = """
                      Client # 1: Name1
                           ----------
                           (no accounts)
                """;
        //when
        String report = sut.getReport(client);

        //then
        assertEquals(expectedReport, report);
    }

    @Test
    public void shouldReportWhenClientHasTwoAccounts() {
        //given
        Reporting sut = new Reporting();
        String expectedReport = """
                      Client # 1: Name1
                           ----------
                           - Account # 1: 100.0
                           - Account # 2: 200.0
                """;
        //when
        String report = sut.getReport(client);

        //then
        assertEquals(expectedReport, report);
    }

    @Test
    public void shouldReportWhenBranchHasNotClient() {
        //given
        Reporting sut = new Reporting();
        String expectedReport = """
                Branch # 1
                 """;
        //when
        String report = sut.getReport(branch);

        //then
        assertEquals(expectedReport, report);
    }

}
