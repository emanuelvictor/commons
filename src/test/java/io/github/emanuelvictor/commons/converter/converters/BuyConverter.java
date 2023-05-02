
package io.github.emanuelvictor.commons.converter.converters;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.BuyDTO;
import io.github.emanuelvictor.commons.converter.model.Buy;
import io.github.emanuelvictor.commons.converter.pool.Pool;

public class BuyConverter extends Converter<BuyDTO, Buy> {

    @Override
    public BuyDTO convertAll(Buy origin) {
        final BuyDTO buyDTO = convertWithoutRecursive(origin);
        final AccountConverter accountConverter = new AccountConverter();
        final AccountDTO accountDTO = accountConverter.convertRecursive(origin.getAccount());
        buyDTO.setAccount(accountDTO);
        return buyDTO;
    }

    @Override
    public BuyDTO convertWithoutRecursive(Buy origin) {
        final BuyDTO buyDTO = new BuyDTO();
        buyDTO.setValue(origin.getValue());
        return (BuyDTO) Pool.getInstance().put(origin, buyDTO);
    }

    @Override
    public BuyDTO convertRecursive(Buy buy) {
        final BuyDTO buyDTOFound = (BuyDTO) Pool.getInstance().get(buy);
        if (buyDTOFound == null) {
            final BuyConverter buyConverter = new BuyConverter();
            return buyConverter.convertAll(buy);
        }
        return buyDTOFound;
    }
}
