package io.github.emanuelvictor.commons.reflection.example;

public class Employee {

    private static final int staticParameter = 0;

    private int id;

    private String document;

    private transient String name;

    public String surname;

    private boolean active;

    private long registry;

    String fieldWithoutVisibility;

    protected String protectedField;

    final String finalField = "";

}
