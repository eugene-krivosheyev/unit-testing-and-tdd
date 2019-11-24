package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import com.acme.banking.dbo.service.builders.MockitoClientBuilder;
import com.acme.banking.dbo.service.builders.MockitoClientCollectionBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingBranchWithClientsTest  {
    private Reporting sut;
    private Branch stubBranch;
    private String report;
    private UUID stubClientId;
    private Client stubClient;
    private Collection<Client> stubClients;

    @Before
    public void setUp() {
        sut = new Reporting();
        stubBranch = mock(Branch.class);
        stubClientId = UUID.randomUUID();

        stubClient = new MockitoClientBuilder().withId(stubClientId).build();
        stubClients = new MockitoClientCollectionBuilder().withClient(stubClient).build();
        when(stubBranch.getClients()).thenReturn(stubClients);
    }


    @Test
    public void shouldGetReportWithNewLinesAndClientIdWhenBranchHasClient() {
        //region when
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId);
        //endregion
    }


    @Test
    public void shouldGetReportWhenBranchHasTwoClients() {
        //region given
        UUID stubClientId2 = UUID.randomUUID();
        stubClients = new MockitoClientCollectionBuilder()
                .withClient(new MockitoClientBuilder().withId(stubClientId).build())
                .withClient(new MockitoClientBuilder().withId(stubClientId2).build())
                .build();
        //endregion

        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).isEqualTo("# \n## " + stubClientId + "\n## " + stubClientId2);
        //endregion
    }


    @Test
    public void shouldGetReportWhenBranchClientsCollectionNotEmpty() {
        //region when
        when(stubBranch.getClients()).thenReturn(stubClients);
        report = sut.getReport(stubBranch);
        //endregion

        //region then
        assertThat(report).startsWith("# \n## " + stubClientId);
        //endregion
    }
}
