package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.service.ReportingServiceImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.fest.assertions.api.Assertions.assertThat;

public class ReportingServiceTest {

    @Test
    public void shouldGetReportForFilialWhenChildrenBranchesEmpty() {
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch1 = new Branch(1, "Филиал1", Collections.emptyList(), Collections.emptyList());
        String result = reportingService.getReport(branch1);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# 1: Филиал1 0");
    }

    @Test
    public void shouldGetReportForFilialLevelOneWhenChildrenBranchesExist() {
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch3 = new Branch(3, "Филиал3", Collections.emptyList(), Collections.emptyList());
        Branch branch2 = new Branch(2, "Филиал2", Collections.emptyList(), Collections.emptyList());
        Branch branch1 = new Branch(1, "Филиал1", Arrays.asList(branch2, branch3), Collections.emptyList());
        String result = reportingService.getReport(branch1);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# 1: Филиал1 0\n" +
                        " ## 2: Филиал2 0\n" +
                        " ## 3: Филиал3 0");
    }

    @Test
    public void shouldGetReportForFilialWhenChildrenBranchesExist() {
        ReportingServiceImpl reportingService = new ReportingServiceImpl();

        Branch branch7 = new Branch(7, "Филиал7", Collections.emptyList(), Collections.emptyList());
        Branch branch6 = new Branch(6, "Филиал6", Collections.emptyList(), Collections.emptyList());
        Branch branch5 = new Branch(5, "Филиал5", Collections.emptyList(), Collections.emptyList());
        Branch branch4 = new Branch(4, "Филиал4", Collections.emptyList(), Collections.emptyList());
        Branch branch3 = new Branch(3, "Филиал3", Arrays.asList(branch6, branch7), Collections.emptyList());
        Branch branch2 = new Branch(2, "Филиал2", Arrays.asList(branch4, branch5), Collections.emptyList());
        Branch branch1 = new Branch(1, "Филиал1", Arrays.asList(branch2, branch3), Collections.emptyList());
        String result = reportingService.getReport(branch1);

        assertThat(result)
                .isNotEmpty()
                .isEqualTo("# 1: Филиал1 0\n" +
                        " ## 2: Филиал2 0\n" +
                        "  ### 4: Филиал4 0\n" +
                        "  ### 5: Филиал5 0\n" +
                        " ## 3: Филиал3 0\n" +
                        "  ### 6: Филиал6 0\n" +
                        "  ### 7: Филиал7 0");
    }
}