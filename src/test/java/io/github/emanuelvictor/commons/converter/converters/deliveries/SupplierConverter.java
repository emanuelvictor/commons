
package io.github.emanuelvictor.commons.converter.converters.deliveries;

import io.github.emanuelvictor.commons.converter.Converter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.RequirementDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.SupplierDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Buy;
import io.github.emanuelvictor.commons.converter.model.deliveries.Supplier;

import java.util.Map;

public class SupplierConverter extends Converter<SupplierDTO, Supplier> {

    public SupplierConverter() {
    }

    public SupplierConverter(Map<Object, Object> pool) {
        super(pool);
    }

    @Override
    public SupplierDTO convert(Supplier origin) {
        final SupplierDTO supplierDTO = convertWithoutRecursive(origin);
        final RequirementConverter requirementConverter = new RequirementConverter(pool);
        final RequirementDTO requirementDTO = requirementConverter.convertRecursive(origin.getRequirement());
        supplierDTO.setRequirement(requirementDTO);
        return supplierDTO;
    }

    @Override
    public SupplierDTO convertWithoutRecursive(Supplier origin) {
        final SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName(origin.getName());
        supplierDTO.setEmail(origin.getEmail());
        return put(origin, supplierDTO);
    }

}
