package io.github.emanuelvictor.commons.converter;

import io.github.emanuelvictor.commons.converter.converters.users.UserConverter;
import io.github.emanuelvictor.commons.converter.dto.users.UserDTO;
import io.github.emanuelvictor.commons.converter.model.users.Item;
import io.github.emanuelvictor.commons.converter.model.users.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class RecursiveTests {

    @Test
    void mustConvertRecursiveObjects() {
        final User user = new User();
        user.setName("Name OF User");
        user.setEmail("emailofuser@email.com");
        final Item item = new Item();
        item.setValue(BigDecimal.TEN);
        item.setCount(15);
        user.setItem(item);
        item.setUser(user);

        final UserDTO userDTOConverted = new UserConverter().convert(user);

        Assertions.assertThat(userDTOConverted).isNotNull().satisfies(userDTO -> {
            Assertions.assertThat(userDTO.getItem()).isNotNull().satisfies(itemDTO -> {
                Assertions.assertThat(itemDTO.getUser()).isEqualTo(userDTOConverted);
            });
        });
    }
}
