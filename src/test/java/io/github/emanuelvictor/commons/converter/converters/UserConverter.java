package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.ItemDTO;
import io.github.emanuelvictor.commons.converter.dto.UserDTO;
import io.github.emanuelvictor.commons.converter.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserConverter extends Converter<UserDTO, User> {

    public UserConverter() {
    }

    public UserConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public UserDTO convertAll(User origin) {
        final UserDTO userDTO = convertWithoutRecursive(origin);
        final ItemConverter itemConverter = new ItemConverter(pool);
        final ItemDTO itemDTO = itemConverter.convertRecursive(origin.getItem());
        userDTO.setItem(itemDTO);
        return userDTO;
    }

    @Override
    public UserDTO convertWithoutRecursive(User origin) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setEmail(origin.getEmail());
        userDTO.setName(origin.getName());
        return put(origin, userDTO);
    }
}
