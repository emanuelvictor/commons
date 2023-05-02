package io.github.emanuelvictor.commons.converter.converters.deliveries;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.DeliveryDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.SupplierDTO;
import io.github.emanuelvictor.commons.converter.model.deliveries.Delivery;

import java.util.Map;
import java.util.Set;

public class DeliveryConverter extends Converter<DeliveryDTO, Delivery> {

    public DeliveryConverter() {
    }

    public DeliveryConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public DeliveryDTO convert(Delivery origin) {
        final DeliveryDTO deliveryDTO = convertWithoutRecursive(origin);
        final SupplierConverter supplierConverter = new SupplierConverter(pool);
        final SupplierDTO supplierDTO = supplierConverter.convertRecursive(origin.getSupplier());
        deliveryDTO.setSupplier(supplierDTO);
        return deliveryDTO;
    }

    @Override
    public DeliveryDTO convertWithoutRecursive(Delivery origin) {
        final DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setDate(origin.getDate());
        return put(origin, deliveryDTO);
    }

}