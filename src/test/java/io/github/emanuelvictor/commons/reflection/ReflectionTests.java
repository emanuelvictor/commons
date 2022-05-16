package io.github.emanuelvictor.commons.reflection;

import io.github.emanuelvictor.commons.reflection.example.Employee;
import org.apache.bcel.Constants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReflectionTests {

    /**
     *
     */
    @Test
    @Deprecated
    public void getAttributesFromClassTest() {
        Reflection.getAttributesFromClass(Employee.class);
    }

    /**
     *
     */
    @Test
    public void getWithoutVisibilityFieldsTests() {
        final String expec = "fieldWithoutVisibility";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, (short)0))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getPrivateFieldsTests() {
        final String expec = "id;document;active;registry";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, Constants.ACC_PRIVATE))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getProtectedFieldsTests() {
        final String expec = "protectedField";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, Constants.ACC_PROTECTED))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getPublicFieldsTests() {
        final String expec = "surname";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, Constants.ACC_PUBLIC))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getFinalFieldsTests() {
        final String expec = "finalField";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, Constants.ACC_FINAL))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getFinalProtectedPrivateAndPublicFieldsTests() {
        final String expec = "id;document;surname;active;registry;fieldWithoutVisibility;protectedField;finalField";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, (short) 0, Constants.ACC_FINAL, Constants.ACC_PUBLIC, Constants.ACC_PROTECTED, Constants.ACC_PRIVATE))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getFieldsTtest() {
        final String expec = "id;document;surname;active;registry;fieldWithoutVisibility;protectedField;finalField";
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class))).isEqualTo(expec);
    }

    /**
     *
     */
    @Test
    public void getAllFieldsTests() {
        final String expec = "staticParameter;id;document;name;surname;active;registry;fieldWithoutVisibility;protectedField;finalField";
        Assertions.assertThat(String.join(";", Reflection.getAllFields(Employee.class))).isEqualTo(expec);
    }
}
