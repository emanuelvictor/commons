package io.github.emanuelvictor.commons.converter.dto.deliveries;

import java.time.LocalDate;

public class DeliveryDTO {

    private LocalDate date;
    private SupplierDTO supplier;
    private RequirementDTO requirement;

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

    public RequirementDTO getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementDTO requirement) {
        this.requirement = requirement;
    }
}
