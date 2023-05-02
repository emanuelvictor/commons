package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.Account;
import io.github.emanuelvictor.commons.converter.model.Buy;
import io.github.emanuelvictor.commons.converter.pool.Pool;

import java.util.Set;
import java.util.stream.Collectors;

public class AccountConverter extends Converter<AccountDTO, Account> {

    @Override
    public AccountDTO convertAll(Account origin) {
        final AccountDTO accountDTO = convertWithoutRecursive(origin);
        final BuyConverter buyConverter = new BuyConverter();
        final Set<BuyDTO> buiesDTOs = buyConverter.convertRecursive(origin.getBuys());
        accountDTO.setBuys(buiesDTOs);
        return accountDTO;
    }

    @Override
    public AccountDTO convertWithoutRecursive(Account origin) {
        final AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(origin.getEmail());
        accountDTO.setName(origin.getName());
        return (AccountDTO) Pool.getInstance().put(origin, accountDTO);
    }

    @Override
    public AccountDTO convertRecursive(Account account) {
        final AccountDTO accountDTOFound = (AccountDTO) Pool.getInstance().get(account);
        if (accountDTOFound == null) {
            final AccountConverter accountConverter = new AccountConverter();
            return accountConverter.convertWithoutRecursive(account);
        }
        return accountDTOFound;
    }
}