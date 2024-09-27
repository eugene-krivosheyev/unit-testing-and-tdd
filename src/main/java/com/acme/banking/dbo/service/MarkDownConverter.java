package com.acme.banking.dbo.service;

public interface MarkDownConverter<T> {

    String toMarkdown(T object);

}
