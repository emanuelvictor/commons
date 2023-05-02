package io.github.emanuelvictor.commons.converter;

import io.github.emanuelvictor.commons.converter.converters.accounts.set.AccountConverter;
import io.github.emanuelvictor.commons.converter.converters.accounts.set.BuyConverter;
import io.github.emanuelvictor.commons.converter.converters.deliveries.SupplierConverter;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.AccountDTO;
import io.github.emanuelvictor.commons.converter.dto.accounts.set.BuyDTO;
import io.github.emanuelvictor.commons.converter.dto.deliveries.SupplierDTO;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Account;
import io.github.emanuelvictor.commons.converter.model.accounts.set.Buy;
import io.github.emanuelvictor.commons.converter.model.deliveries.Delivery;
import io.github.emanuelvictor.commons.converter.model.deliveries.Requirement;
import io.github.emanuelvictor.commons.converter.model.deliveries.Supplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeliverySetRecursiveTests {

    @Test
    void mustConvertSetRecursiveObjects() {
        final Supplier supplier = new Supplier();
        supplier.setName("Name OF User");
        supplier.setEmail("emailofuser@email.com");

        final Requirement requirement = new Requirement();
        requirement.setCount(15);

        final Delivery firstDelivery = new Delivery();
        firstDelivery.setDate(LocalDate.now().plusDays(1));
        final Delivery secondDelivery = new Delivery();
        secondDelivery.setDate(LocalDate.now().plusDays(3));

        supplier.setRequirement(requirement);
        requirement.setSupplier(supplier);
        requirement.setDeliveries(new HashSet<>(Arrays.asList(firstDelivery, secondDelivery)));
        firstDelivery.setSupplier(supplier);
        firstDelivery.setRequirement(requirement);
        secondDelivery.setSupplier(supplier);
        secondDelivery.setRequirement(requirement);

        final SupplierDTO supplierDTOConverted = new SupplierConverter().convert(supplier);

        System.out.println(""); // TODO its working, must be concluded tests

    }


}
