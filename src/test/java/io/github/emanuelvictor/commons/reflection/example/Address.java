package io.github.emanuelvictor.commons.reflection.example;

public class Address {

    private final String name;

    private final long code;

    private final String street;

    private final int number;

    public Address(final String name, final long code, final String street, final int number) {
        this.name = name;
        this.code = code;
        this.street = street;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }
}