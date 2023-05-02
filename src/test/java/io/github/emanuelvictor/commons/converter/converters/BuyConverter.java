
package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.Buy;

import java.util.Map;

public class BuyConverter extends Converter<BuyDTO, Buy> {

    public BuyConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public BuyDTO convertAll(Buy origin) {
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
