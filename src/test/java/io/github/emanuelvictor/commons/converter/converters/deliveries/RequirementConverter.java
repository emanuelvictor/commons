package io.github.emanuelvictor.commons.converter.converters.deliveries;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.converters.accounts.set.BuyConverter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.DeliveryDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.RequirementDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Account;
import io.github.emanuelvictor.commons.converter.model.deliveries.Requirement;

import java.util.Map;
import java.util.Set;

public class RequirementConverter extends Converter<RequirementDTO, Requirement> {

    public RequirementConverter() {
    }

    public RequirementConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public RequirementDTO convert(Requirement origin) {
        final RequirementDTO requirementDTO = convertWithoutRecursive(origin);
        final DeliveryConverter deliveryConverter = new DeliveryConverter(pool);
        final Set<DeliveryDTO> deliveryDTOS = deliveryConverter.convertRecursive(origin.getDeliveries());
        requirementDTO.setDeliveries(deliveryDTOS);
        return requirementDTO;
    }

    @Override
    public RequirementDTO convertWithoutRecursive(Requirement origin) {
        final RequirementDTO requirementDTO = new RequirementDTO();
        requirementDTO.setCount(origin.getCount());
        return put(origin, requirementDTO);
    }

}