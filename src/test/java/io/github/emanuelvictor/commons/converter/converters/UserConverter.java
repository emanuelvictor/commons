package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.ItemDTO;
import io.github.emanuelvictor.commons.converter.dto.UserDTO;
import io.github.emanuelvictor.commons.converter.model.Item;
import io.github.emanuelvictor.commons.converter.model.User;
import io.github.emanuelvictor.commons.converter.pool.Pool;

public class UserConverter extends Converter<UserDTO, User> {

    @Override
    public UserDTO convertAll(User origin) {
        final UserDTO userDTO = convertWithoutRecursive(origin);
        final ItemConverter itemConverter = new ItemConverter();
        final ItemDTO itemDTO = itemConverter.convertRecursive(origin.getItem());
        userDTO.setItem(itemDTO);
        return userDTO;
    }

    @Override
    public UserDTO convertWithoutRecursive(User origin) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setEmail(origin.getEmail());
        userDTO.setName(origin.getName());
        return (UserDTO) Pool.getInstance().put(origin, userDTO);
    }

    public UserDTO convertRecursive(User user) {
        final UserDTO userDTOFound = (UserDTO) Pool.getInstance().get(user);
        if (userDTOFound == null) {
            final UserConverter userConverter = new UserConverter();
            return userConverter.convertWithoutRecursive(user);
        }
        return userDTOFound;
    }
}
