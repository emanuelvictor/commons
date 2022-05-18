package io.github.emanuelvictor.commons.reflection;

import io.github.emanuelvictor.commons.reflection.aspect.Ignore;
import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.FieldOrMethod;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        final JavaClass javaClass = getJavaClass(clazz.getName(), repository);
        return returnFields(clazz, Arrays.stream(javaClass.getFields())
                .filter(field -> Arrays.stream(modifiers).anyMatch(f -> field.getModifiers() == f)));
    }

    /**
     * Get {@link JavaClass} from className in String via bcel library.
     *
     * @param className  {@link String}
     * @param repository {@link ClassLoaderRepository}
     * @return {@link JavaClass}
     */
    static JavaClass getJavaClass(final String className, final ClassLoaderRepository repository) {
        try {
            return repository.loadClass(className);
        } catch (final ClassNotFoundException e) { // TODO maske test
            throw new io.github.emanuelvictor.commons.reflection.exception.ClassNotFoundException(e.getMessage(), e);
        }
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
        return getFields(clazz, (short) 0, (short) 18, Constants.ACC_PUBLIC, Constants.ACC_FINAL, Constants.ACC_PRIVATE, Constants.ACC_PROTECTED);
    }

    /**
     * Return all fields in order
     *
     * @param clazz {@link Class}
     * @return {@link List}List list of fields
     */
    public static List<String> getAllFields(final Class<?> clazz) {
        final ClassLoaderRepository repository = new ClassLoaderRepository(clazz.getClassLoader());
        final JavaClass javaClass = getJavaClass(clazz.getName(), repository);
        return returnFields(clazz, Arrays.stream(javaClass.getFields()));
    }

    /**
     * Get annotation from field
     *
     * @param clazz           {@link Class}
     * @param annotationClass {@link Class}
     * @param fieldName       {@link String}
     * @return {@link Annotation}
     */
    public static Annotation getAnnotationFromField(final Class<?> clazz, Class annotationClass, final String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName).getAnnotation(annotationClass);
        } catch (final NoSuchFieldException e) {
            throw new io.github.emanuelvictor.commons.reflection.exception.NoSuchFieldException(e.getMessage(), e);
        }
    }

    /**
     * @param clazz  {@link Class}
     * @param fields {@link Stream}
     * @return {@link List}
     */
    static List<String> returnFields(final Class<?> clazz, final Stream<Field> fields) {
        return fields
                .filter(field -> {
                    final Annotation ignoreAnnotation = getAnnotationFromField(clazz, Ignore.class, field.getName());
                    return ignoreAnnotation == null;
                })
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
    public static Set<String> getAttributesFromClass(final Class<?> clazz) {  // TODO maske test
        return Arrays.stream(clazz.getDeclaredMethods())
                .map(Method::getName)
                .filter(method -> method.contains("get") || method.contains("set"))
                .map(method -> {
                    final String attribute = method.replace("get", "").replace("set", "");
                    return attribute.substring(0, 1).toLowerCase() + attribute.substring(1);
                }).collect(Collectors.toSet());
    }

    /**
     * Extract the attributes from class.
     *
     * @param object    {@link Object}
     * @param attribute {@link String}
     * @return {@link Object}
     */
    public static Object getValueFromField(final Object object, final String attribute) {

        for (final Method method : object.getClass().getMethods()) {
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

    /**
     * @param object    {@link Object}
     * @param attribute {@link String}
     * @return {@link Object}
     * @deprecated use getValueFromField
     */
    @Deprecated
    public static Object getValueFromAttribute(final Object object, final String attribute) {
        return getValueFromField(object, attribute);
    }

}

