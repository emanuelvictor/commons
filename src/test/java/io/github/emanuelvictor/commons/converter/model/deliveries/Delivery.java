package io.github.emanuelvictor.commons.converter.model.deliveries;

import java.time.LocalDate;

public class Delivery {

    private LocalDate date;

    private Supplier supplier;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
