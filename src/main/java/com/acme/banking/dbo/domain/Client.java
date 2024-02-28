package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        checkClientArguments(id, name);

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void checkClientArguments(int id, String name) {
        if (id < 0) throw new InternalError("ID не может быть отрицательным числом");
        if (name == null) throw new InternalError("Name не должно быть null");
        if (name.equals("")) throw new InternalError("Name должно содержать хотя бы 1 букву");
        if (name.equals(" ")) throw new InternalError("Name должно содержать хотя бы 1 букву");
        if (name.equals("-")) throw new InternalError("Name должно содержать хотя бы 1 букву");
        if (name.indexOf(" ") == 0) throw new InternalError("Name не должно начинаться с пробела");
        if (name.indexOf(" ") == name.length() - 1) throw new InternalError("Name не должно оканчиваться пробелом");
        if (name.indexOf("-") == 0) throw new InternalError("Name не должно начинаться с дефиса");
        if (name.indexOf("-") == name.length() - 1) throw new InternalError("Name не должно оканчиваться дефисом");

        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz -";
        for (Character ch : name.toCharArray()) {
            if (!alphabet.contains(ch.toString().toLowerCase())) {
                throw new InternalError("Name содержит символы помимо кириллицы, латиницы, пробела и дефиса");
            }
        }
    }
}
