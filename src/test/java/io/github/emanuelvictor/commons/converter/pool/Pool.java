package io.github.emanuelvictor.commons.converter.pool;

import io.github.emanuelvictor.commons.converter.dto.ItemDTO;
import io.github.emanuelvictor.commons.converter.model.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Pool {

    private static Pool INSTANCE;

    private Map<Object, Object> pool = new HashMap<>();

    private Pool() {
    }

    public static Pool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Pool();
        }

        return INSTANCE;
    }

    public Object put(final Object key, final Object object){
        pool.put(key, object);
        return object;
    }

    public Object get(Object object) {
        return pool.get(object);
    }
}
