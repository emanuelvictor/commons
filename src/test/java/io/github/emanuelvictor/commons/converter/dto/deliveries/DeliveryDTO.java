package io.github.emanuelvictor.commons.converter.dto.deliveries;

import java.time.LocalDate;

public class DeliveryDTO {

    private LocalDate date;

    private SupplierDTO supplier;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}
