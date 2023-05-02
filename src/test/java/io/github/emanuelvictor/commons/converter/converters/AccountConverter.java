package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.Account;

import java.util.Map;
import java.util.Set;

public class AccountConverter extends Converter<AccountDTO, Account> {

    public AccountConverter() {
    }

    public AccountConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public AccountDTO convertAll(Account origin) {
        final AccountDTO accountDTO = convertWithoutRecursive(origin);
        final BuyConverter buyConverter = new BuyConverter(pool);
        final Set<BuyDTO> buiesDTOs = buyConverter.convertRecursive(origin.getBuys());
        accountDTO.setBuys(buiesDTOs);
        return accountDTO;
    }

    @Override
    public AccountDTO convertWithoutRecursive(Account origin) {
        final AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(origin.getEmail());
        accountDTO.setName(origin.getName());
        return put(origin, accountDTO);
    }

}