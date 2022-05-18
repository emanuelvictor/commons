package io.github.emanuelvictor.commons.reflection.example;

import io.github.emanuelvictor.commons.reflection.aspect.Ignore;

public class Employee {

    @Ignore
    public String fieldToIgnore4;

    private static final int staticParameter = 0;

    private int id;

    private String document;

    private transient String name;

    @Ignore
    public String fieldToIgnore;

    public String surname;

    private boolean active;

    private long registry;

    String fieldWithoutVisibility;

    @Ignore
    public String fieldToIgnore2;

    protected String protectedField;

    final String finalField = "";

    @Ignore
    public String fieldToIgnore3;

}
