package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.ItemDTO;
import io.github.emanuelvictor.commons.converter.dto.UserDTO;
import io.github.emanuelvictor.commons.converter.model.Item;
import io.github.emanuelvictor.commons.converter.model.User;
import io.github.emanuelvictor.commons.converter.pool.Pool;

public class ItemConverter extends Converter<ItemDTO, Item> {
    @Override
    public ItemDTO convertAll(Item origin) {
        final ItemDTO itemDTO = convertWithoutRecursive(origin);
        final UserConverter userConverter = new UserConverter();
        final UserDTO userDTO = userConverter.convertRecursive(origin.getUser());
        itemDTO.setUser(userDTO);
        return itemDTO;
    }

    @Override
    public ItemDTO convertWithoutRecursive(Item origin) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCount(origin.getCount());
        itemDTO.setValue(origin.getValue());
        return (ItemDTO) Pool.getInstance().put(origin, itemDTO);
    }

    public ItemDTO convertRecursive(Item item) {
        final ItemDTO itemDTOFound = (ItemDTO) Pool.getInstance().get(item);
        if (itemDTOFound == null) {
            final ItemConverter itemConverter = new ItemConverter();
            return itemConverter.convertAll(item);
        }
        return itemDTOFound;
    }

}
