package io.github.emanuelvictor.commons.reflection;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.FieldOrMethod;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class Reflection {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * This method use bcel apache library to extract fields from class in order.
     * The modifiers parameters is the modifiers of the fields (public, protected, transient, private, etc...).
     * See more on {@link org.apache.bcel.classfile.AccessFlags} and {@link org.apache.bcel.Constants}.
     *
     * @param clazz     {@link Class}
     * @param modifiers {@link Short[]} modifiers of the fields (public, protected, transient, private, etc...)
     * @return {@link List} List list of fields
     */
    public static List<String> getFields(final Class<?> clazz, final Short... modifiers) {
        final ClassLoaderRepository repository = new ClassLoaderRepository(clazz.getClassLoader());
        final JavaClass javaClass;
        try {
            javaClass = repository.loadClass(clazz.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(javaClass.getFields())
                .filter(field -> Arrays.stream(modifiers).anyMatch(f -> field.getModifiers() == f))
                .map(FieldOrMethod::getName)
                .collect(Collectors.toList());
    }

    /**
     * Overload from {@link #getFields(Class, Short...)}
     * This method extract fields in order too, but it is not need modifiers parameters.
     * The modifiers will be 1,2,4 and 10. Reference to public, private, protected and final visibility modifiers.
     *
     * @param clazz {@link Class}
     * @return {@link List}List list of fields
     */
    public static List<String> getFields(final Class<?> clazz) {
        return getFields(clazz, (short) 0, Constants.ACC_PUBLIC, Constants.ACC_FINAL, Constants.ACC_PRIVATE, Constants.ACC_PROTECTED);
    }

    /**
     * Return all fields in order
     *
     * @param clazz {@link Class}
     * @return {@link List}List list of fields
     */
    public static List<String> getAllFields(final Class<?> clazz) {
        final ClassLoaderRepository repository = new ClassLoaderRepository(clazz.getClassLoader());
        final JavaClass javaClass;
        try {
            javaClass = repository.loadClass(clazz.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(javaClass.getFields())
                .map(FieldOrMethod::getName)
                .collect(Collectors.toList());
    }

    /**
     * Extract the attributes from class. This method not return the fields in order. This method return only attributes with setters and getters.
     *
     * @param clazz {@link Class}
     * @return {@link Set}
     */
    @Deprecated
    public static Set<String> getAttributesFromClass(final Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .map(Method::getName)
                .filter(method -> method.contains("get") || method.contains("set"))
                .map(method -> {
                    final String attribute = method.replace("get", "").replace("set", "");
                    return attribute.substring(0, 1).toLowerCase() + attribute.substring(1);
                }).collect(Collectors.toSet());
    }

    /**
     * Extract the attributes from class
     *
     * @param object    {@link Object}
     * @param attribute {@link String}
     * @return {@link Object}
     */
    public static Object getValueFromAttribute(final Object object, final String attribute) {

        for (Method method : object.getClass().getMethods()) {
            if (method.getName().toLowerCase().replace("get", "").equalsIgnoreCase(attribute.toLowerCase())) {
                try {
                    return method.invoke(object);
                } catch (final IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        return null;
    }
}

