package demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


//test can be in four state
// - ignored (assume broken or disabled)
// -- runtime error
// - yellow
// -- red

// Test must be named with SUT (system / subject under test) + Test (For Maven plugin)
/*
 * Test class, Test case, [Test suite (in junit5 subclasses can be used)]
 */
public class ArrayListTest {
    /*
     * Test, Test scenario, Test method
     * Naming: BDD
     */

    // Module tests - test only function components
    // NFR (non functional ___), what is not determined in code is not getting tested
    // NFR are not in determined code, but can cause

    // We have to use annotate method with Test annotation
    // Naming "count" Antipattern - to name it like test01
    // Name should represent cyclomatic complexity path
    // shouldRESULTWhenSTIMULUS()
    // STIMULUS / condition / state? - Pre condition, incoming data
    // RESULT - Post condition, change state (or data)
    //
    // Add element
    // Should "adding element" work every time the same,
    // or it's dependant from source data?
    // - Is null added the same way?
    // Post condition - can be many
    // - Should size is incremented
    // - Should elements is actually added
    // So we need to group, "Aggregate" all of these conditions - "Store"
    // Why we group? Because by CONTRACT we guarantee that on add:
    // - size is inc on adding object
    // - object is actually added
    // If we don't test is as whole (do 2-3 single test), it's possible we don't test contract
    // So we document the contract by test cases
    // We name object (test case) as domain object - it's DDD
    // naming: BDD
    //
    @Test
    public void shouldStoreWhenAddElementNull() {
        // old representation
        // AAA

        // test flow - we test
        // multiple flows - (cause of) coehesion

        // region 1: Fixture | Arrange | Given (Дано:)
        final Object dummy = new Object();
        final Collection<Object> sut = new ArrayList<>();
        assumeTrue(sut.isEmpty());
        // if our class is used only by interface
        // we test by that interface - but only base class
        // we should write test for superclass
        // endregion


        // region 2: Act | When
        // endregion
        sut.add(dummy);

        // region 3: Assert | Then
        // logic domain assert (should be decomposed to) -> (logical/ physical assertions)

        // the add, contains, equals methods depend on object state
        // it's cohesion.
        // so we actually need to complete ALL test, because they're dependant

        // Why can't we get from test to actual object state, for example 'size' property:
        // - it's private/ hidden property, so details of implementation is not guaranteed and can be changed
        // - it can be evaluated during execution (size can be calculated)
        // so making test based on object internals can
        // we can do it (using reflection), but is unique case and should be documented

        // we assume that those methods are not defective!
        assertTrue(sut.contains(dummy));
        assertEquals( 1, sut.size());

        // endregion


    }
    // get
}
