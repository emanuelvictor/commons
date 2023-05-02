package io.github.emanuelvictor.commons.converter.dto.deliveries;

import java.util.Set;

public class RequirementDTO {
    private int count;
    private Set<DeliveryDTO> deliveries;

    private SupplierDTO supplier;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<DeliveryDTO> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<DeliveryDTO> deliveries) {
        this.deliveries = deliveries;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}
