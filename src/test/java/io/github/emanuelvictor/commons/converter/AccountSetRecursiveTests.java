package io.github.emanuelvictor.commons.converter;

import io.github.emanuelvictor.commons.converter.converters.accounts.set.AccountConverter;
import io.github.emanuelvictor.commons.converter.converters.accounts.set.BuyConverter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Account;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Buy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AccountSetRecursiveTests {

    @Test
    void mustConvertSetRecursiveObjects() {
        final Buy firstBuy = new Buy();
        firstBuy.setValue(BigDecimal.ZERO);
        final Buy seccondBuy = new Buy();
        seccondBuy.setValue(BigDecimal.TEN);
        final Account account = new Account();
        account.setName("Name OF User");
        account.setEmail("emailofuser@email.com");
        account.setBuys(new HashSet<>(Arrays.asList(firstBuy, seccondBuy)));
        firstBuy.setAccount(account);
        seccondBuy.setAccount(account);

        final AccountDTO accountDTOConverted = new AccountConverter().convert(account);

        Assertions.assertThat(accountDTOConverted).isNotNull().satisfies(accountDTO ->
                Assertions.assertThat(accountDTO.getBuys()).isNotEmpty().satisfies(buysDTOs -> {
                    Assertions.assertThat(buysDTOs.size()).isEqualTo(accountDTOConverted.getBuys().size());
                    buysDTOs.forEach(buyDTO -> {
                        Assertions.assertThat(buyDTO.getAccount()).isEqualTo(accountDTOConverted);
                    });
                }));
    }

    @Test
    void mustConvertSetRecursiveObjectsBeginningBySet() {
        final Buy firstBuy = new Buy();
        firstBuy.setValue(BigDecimal.ZERO);
        final Buy seccondBuy = new Buy();
        seccondBuy.setValue(BigDecimal.TEN);
        final Account account = new Account();
        account.setName("Name OF User");
        account.setEmail("emailofuser@email.com");
        account.setBuys(new HashSet<>(Arrays.asList(firstBuy, seccondBuy)));
        firstBuy.setAccount(account);
        seccondBuy.setAccount(account);

        final Set<BuyDTO> buysDTOsConverted = new BuyConverter().convert(new HashSet<>(Arrays.asList(firstBuy, seccondBuy)));

        Assertions.assertThat(buysDTOsConverted).isNotEmpty().extracting(BuyDTO::getAccount)
                .satisfies(accounts -> {
                    accounts.forEach(accountDTOConverted -> {
                        Assertions.assertThat(accountDTOConverted).isNotNull().satisfies(accountDTO -> {
                            Assertions.assertThat(accountDTO.getBuys()).isEqualTo(buysDTOsConverted);
                        });
                    });
                });
    }

}
