package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Client;

import java.util.ArrayList;
import java.util.Collection;

public class MockitoClientCollectionBuilder {
    private Collection<Client> stubClient = new ArrayList<>();
    private int count = 0;

    public MockitoClientCollectionBuilder withClient(Client stubClient) {
        this.stubClient.add(stubClient);
        return this;
    }

    public MockitoClientCollectionBuilder withCount(int count) {
        this.count = count;
        return this;
    }


    public Collection<Client> build() {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                stubClient.add(new MockitoClientBuilder().build());
            }
        }
        return stubClient;
    }
}
