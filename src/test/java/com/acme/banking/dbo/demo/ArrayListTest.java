package com.acme.banking.dbo.demo;

import org.fest.assertions.api.Assertions;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test
    public void shouldSizeIncrementedAndElementContainedWhenElementAdded() {
        final ArrayList<Object> sut = new ArrayList<>();
        final Object dummy = new Object();
        assumeTrue(sut.isEmpty());

        sut.add(dummy);

        assertEquals(1, sut.size());
        assertTrue(sut.contains(dummy));
        // OR
        assertThat(sut)
                .contains(dummy)
                .hasSize(1);
    }
}
