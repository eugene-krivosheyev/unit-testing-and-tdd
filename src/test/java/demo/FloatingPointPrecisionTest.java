package demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FloatingPointPrecisionTest {
    @Test
    public void fpsShouldGetNoPrecisionErrorWhenSum() {
        System.out.println(.1 + .2); //IEEE 754
        assertEquals(.3, .1 + .2, 0.001);
    }
}
