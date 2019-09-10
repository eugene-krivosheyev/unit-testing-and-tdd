package com.acme.banking.dbo;

import com.acme.banking.dbo.utils.ObjectUtils;
import org.junit.Test;

public class ObjectUtilsTest {

    private ObjectUtils utils = new ObjectUtils();

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenObjectIsNull() {
        utils.requireNonNull(null, "Error");
    }
}
