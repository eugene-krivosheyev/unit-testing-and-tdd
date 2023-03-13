package com.acme.banking.dbo.util;

public class Utils {
    public static void require(boolean precondition) {
        if (!precondition) {
            throw new IllegalArgumentException();
        }
    }
}
