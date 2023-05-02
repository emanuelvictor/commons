package io.github.emanuelvictor.commons.converter;

import io.github.emanuelvictor.commons.converter.converters.AccountConverter;
import io.github.emanuelvictor.commons.converter.converters.UserConverter;
import io.github.emanuelvictor.commons.converter.dto.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.UserDTO;
import io.github.emanuelvictor.commons.converter.model.Account;
import io.github.emanuelvictor.commons.converter.model.Buy;
import io.github.emanuelvictor.commons.converter.model.Item;
import io.github.emanuelvictor.commons.converter.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class ConvertersTests {

    @Test
    void mustConvertDirectCruzedObjects() {
        final User user = new User();
        user.setName("Name OF User");
        user.setEmail("emailofuser@email.com");
        final Item item = new Item();
        item.setValue(BigDecimal.TEN);
        item.setCount(15);
        user.setItem(item);
        item.setUser(user);

        final UserDTO userDTOConverted = new UserConverter().convertAll(user);

        Assertions.assertThat(userDTOConverted).isNotNull().satisfies(userDTO -> {
            Assertions.assertThat(userDTO.getItem()).isNotNull().satisfies(itemDTO -> {
                Assertions.assertThat(itemDTO.getUser()).isEqualTo(userDTOConverted);
            });
        });
    }

    @Test
    void mustConvertDirectListCruzedObjects() {
        final Buy firstBuy = new Buy();
        firstBuy.setValue(BigDecimal.TEN);
        final Buy seccondBuy = new Buy();
        seccondBuy.setValue(BigDecimal.TEN);
        final Account account = new Account();
        account.setName("Name OF User");
        account.setEmail("emailofuser@email.com");
        account.setBuys(new HashSet<>(Arrays.asList(firstBuy, seccondBuy)));
        firstBuy.setAccount(account);
        seccondBuy.setAccount(account);

        final AccountDTO accountDTOConverted = new AccountConverter().convertAll(account);

        Assertions.assertThat(accountDTOConverted).isNotNull().satisfies(accountDTO ->
                Assertions.assertThat(accountDTO.getBuys()).isNotEmpty().satisfies(buysDTOs -> {
                    Assertions.assertThat(buysDTOs.size()).isEqualTo(accountDTOConverted.getBuys().size());
                    buysDTOs.forEach(buyDTO -> {
                        Assertions.assertThat(buyDTO.getAccount()).isEqualTo(accountDTOConverted);
                    });
                }));
    }
}
