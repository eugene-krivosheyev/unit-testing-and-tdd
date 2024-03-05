package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.BranchToMarkdownConverter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BranchConverterToMarkdown {

    @Test
    void givenBranchShouldTranslateToMarkdown() {
        var converter = new BranchToMarkdownConverter();
        var mockedBranch = mock(Branch.class);
        when(mockedBranch.getName()).thenReturn("someName");

        assertEquals("# someName", converter.toMarkdown(mockedBranch));
    }

    @Test
    void givenBranchWithBranchShpuldTranslateToMarkdown() {
        var converter = new BranchToMarkdownConverter();
        var mockedBranch = mock(Branch.class);
        when(mockedBranch.getName()).thenReturn("someName");
        when(mockedBranch.getChildren()).thenReturn(List.of(mockedBranch));

        assertEquals("# someName ## someName", converter.toMarkdown(mockedBranch));
    }

}
