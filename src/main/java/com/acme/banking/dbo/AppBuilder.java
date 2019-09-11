package com.acme.banking.dbo;

import com.acme.banking.dbo.service.Reporting;

public class AppBuilder {
    public static void main(String[] args) {
        new Reporting(
            new ???Repo("jdbc:xxx"),
            "#"
        );
    }
}
