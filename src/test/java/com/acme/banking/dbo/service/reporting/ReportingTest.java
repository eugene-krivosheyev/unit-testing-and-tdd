package com.acme.banking.dbo.service.reporting;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.Reporting;
import com.acme.banking.dbo.service.builders.MockitoClientCollectionBuilder;
import com.acme.banking.dbo.service.builders.MockitoClientBuilder;
import com.acme.banking.dbo.service.builders.MockitoSavingAccountBuilder;
import org.junit.Before;

import java.util.Collection;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ReportingTest {
    private Reporting sut;
    private Branch stubBranch;
    private Client stubClient;
    private String report;
    private UUID stubClientId;
    private Collection<Account> stubAccounts;

//    @Before
//    public void setUp() throws ClassNotFoundException {
//        sut = new Reporting();
//        stubBranch = mock(Branch.class);
//        stubClientId = UUID.randomUUID();
//        stubClient = new MockitoClientBuilder().withId(stubClientId).build();
//        stubAccounts = new MockitoSavingAccountBuilder()
//                .withClient(stubClient)
//                .build();
//    }


}
