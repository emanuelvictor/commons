package io.github.emanuelvictor.commons.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Reflection {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Extract the attributes from class
     *
     * @return {@link Set}
     */
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
     * @return {@link Set}
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

