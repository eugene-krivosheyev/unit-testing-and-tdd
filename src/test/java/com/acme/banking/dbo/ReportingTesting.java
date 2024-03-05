package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.MarkDownConverter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingTesting {


    @Test
    void someTest(){
        var qq  = mock(Branch.class);
        
        when(qq.getAccounts()).thenReturn(Collections.emptyList());
    }


    @Nested
    class BranchReporting{

        @Test
        void givenBranchShouldTranslateToMarkdown(){

            var mockBranchConverter = (MarkDownConverter<Branch>) mock(MarkDownConverter.class);

            assertEquals("#BranchName", mock);
        }

        @Test
        void givenBranchWithBranchShpuldTranslateToMarkdown(){

        }

    }


    @Nested
    class AccountReporting{

    }


}
