package io.github.emanuelvictor.commons.reflection;

import io.github.emanuelvictor.commons.reflection.aspect.Ignore;
import io.github.emanuelvictor.commons.reflection.example.Address;
import io.github.emanuelvictor.commons.reflection.example.Employee;
import io.github.emanuelvictor.commons.reflection.exception.NoSuchFieldException;
import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


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
        Assertions.assertThat(String.join(";", Reflection.getFields(Employee.class, (short) 0))).isEqualTo(expec);
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

    /**
     *
     */
    @Test
    public void returnFieldsTests() {
        final Class clazz = Employee.class;

        final ClassLoaderRepository repository = new ClassLoaderRepository(clazz.getClassLoader());
        final JavaClass javaClass = Reflection.getJavaClass(clazz.getName(), repository);

        final Stream<Field> fields = Arrays.stream(javaClass.getFields());
        final List<String> fieldsInString = Reflection.returnFields(clazz, fields);

        Assertions.assertThat(StringUtils.join(fieldsInString, ';')).isEqualTo("staticParameter;id;document;name;surname;active;registry;fieldWithoutVisibility;protectedField;finalField");
    }

    /**
     *
     */
    @Test
    public void getAnnotationFromFieldTest() {
        final Annotation annotation = Reflection.getAnnotationFromField(Employee.class, Ignore.class, "fieldToIgnore4");
        Assertions.assertThat(annotation).isNotNull();
    }

    /**
     *
     */
    @Test
    public void getAnnotationFromFieldMustNotPassTest() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchFieldException.class, () -> Reflection.getAnnotationFromField(Employee.class, Ignore.class, "notFoundField"));
    }

    /**
     *
     */
    @Test
    public void getJavaClassMustNotPassTest() {
        final Class clazz = Employee.class;
        final ClassLoaderRepository repository = new ClassLoaderRepository(clazz.getClassLoader());
        org.junit.jupiter.api.Assertions.assertThrows(io.github.emanuelvictor.commons.reflection.exception.ClassNotFoundException.class, () -> Reflection.getJavaClass("NotFoundClassName", repository));
    }

    /**
     *
     */
    @Test
    public void extractValuesFromObjectTest() {
        final Address address = new Address("name" + 1, 1, "street" + 1, 1);
        Assertions.assertThat(Reflection.getValueFromField(address, "name")).isNotNull();
        Assertions.assertThat(Reflection.getValueFromField(address, "name")).isEqualTo("name" + 1);
    }

    /**
     *
     */
    @Test
    public void extractFieldsFromFinalPrivateFieldTest() {
        final List<String> fields = Reflection.getFields(Address.class);

        Assertions.assertThat(fields).isNotNull();
        Assertions.assertThat(fields).isNotEmpty();
        for (final String field : fields) {
            Assertions.assertThat(field).isNotNull();
        }
    }
}
