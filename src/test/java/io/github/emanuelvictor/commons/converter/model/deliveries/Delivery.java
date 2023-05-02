package io.github.emanuelvictor.commons.converter.model.deliveries;

import java.time.LocalDate;

public class Delivery {

    private LocalDate date;
    private Supplier supplier;
    private Requirement requirement;

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

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
}
