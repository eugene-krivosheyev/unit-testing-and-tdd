package com.acme.banking.dbo.utils;

import java.util.Objects;

public class ObjectUtils {

    public String requireNonNull(String value, String message) {
        if (value == null) throw new NullPointerException(message);
        if (value.length() == 0) throw new IllegalArgumentException(message);
        return value;
    }

    public <T> T requireNonNull(T value, String message) {
        return Objects.requireNonNull(value, message);
    }
}
