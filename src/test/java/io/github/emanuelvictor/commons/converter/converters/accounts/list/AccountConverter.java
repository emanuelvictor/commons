package io.github.emanuelvictor.commons.converter.converters.accounts.list;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.accounts.list.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.list.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.list.Account;

import java.util.Map;
import java.util.List;

public class AccountConverter extends Converter<AccountDTO, Account> {

    public AccountConverter() {
    }

    public AccountConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public AccountDTO convert(Account origin) {
        final AccountDTO accountDTO = convertWithoutRecursive(origin);
        final BuyConverter buyConverter = new BuyConverter(pool);
        final List<BuyDTO> buiesDTOs = buyConverter.convertRecursive(origin.getBuys());
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