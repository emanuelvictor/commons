
package io.github.emanuelvictor.commons.converter.converters.accounts.set;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Buy;

import java.util.Map;

public class BuyConverter extends Converter<BuyDTO, Buy> {

    public BuyConverter() {
    }

    public BuyConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public BuyDTO convert(Buy origin) {
        final BuyDTO buyDTO = convertWithoutRecursive(origin);
        final AccountConverter accountConverter = new AccountConverter(pool);
        final AccountDTO accountDTO = accountConverter.convertRecursive(origin.getAccount());
        buyDTO.setAccount(accountDTO);
        return buyDTO;
    }

    @Override
    public BuyDTO convertWithoutRecursive(Buy origin) {
        final BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(origin.getValue());
        return put(origin, buyDTO);
    }

}
