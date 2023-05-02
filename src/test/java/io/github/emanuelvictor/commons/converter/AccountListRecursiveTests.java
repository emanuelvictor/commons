package io.github.emanuelvictor.commons.converter;

import io.github.emanuelvictor.commons.converter.converters.accounts.list.AccountConverter;
import io.github.emanuelvictor.commons.converter.converters.accounts.list.BuyConverter;
import io.github.emanuelvictor.commons.converter.dto.accounts.list.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.list.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.list.Account;
import io.github.emanuelvictor.commons.converter.model.accounts.list.Buy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountListRecursiveTests {

    @Test
    void mustConvertSetRecursiveObjects() {
        final Buy firstBuy = new Buy();
        firstBuy.setValue(BigDecimal.ZERO);
        final Buy seccondBuy = new Buy();
        seccondBuy.setValue(BigDecimal.TEN);
        final Account account = new Account();
        account.setName("Name OF User");
        account.setEmail("emailofuser@email.com");
        account.setBuys(Arrays.asList(firstBuy, seccondBuy));
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
    void mustConvertListRecursiveObjectsBeginningByList() {
        final Buy firstBuy = new Buy();
        firstBuy.setValue(BigDecimal.ZERO);
        final Buy seccondBuy = new Buy();
        seccondBuy.setValue(BigDecimal.TEN);
        final Account account = new Account();
        account.setName("Name OF User");
        account.setEmail("emailofuser@email.com");
        account.setBuys(Arrays.asList(firstBuy, seccondBuy));
        firstBuy.setAccount(account);
        seccondBuy.setAccount(account);

        final List<BuyDTO> buysDTOsConverted = new BuyConverter().convert(Arrays.asList(firstBuy, seccondBuy));

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
