package io.github.emanuelvictor.commons.converter.converters.users;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.users.ItemDTO;
import io.github.emanuelvictor.commons.converter.dto.users.UserDTO;
import io.github.emanuelvictor.commons.converter.model.users.Item;

import java.util.Map;

public class ItemConverter extends Converter<ItemDTO, Item> {

    public ItemConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public ItemDTO convert(Item origin) {
        final ItemDTO itemDTO = convertWithoutRecursive(origin);
        final UserConverter userConverter = new UserConverter(pool);
        final UserDTO userDTO = userConverter.convertRecursive(origin.getUser());
        itemDTO.setUser(userDTO);
        return itemDTO;
    }

    @Override
    public ItemDTO convertWithoutRecursive(Item origin) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCount(origin.getCount());
        itemDTO.setValue(origin.getValue());
        return put(origin, itemDTO);
    }
}
