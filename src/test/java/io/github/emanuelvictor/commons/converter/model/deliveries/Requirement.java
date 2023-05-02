package io.github.emanuelvictor.commons.converter.model.deliveries;

import java.util.Set;

public class Requirement {
    private int count;
    private Set<Delivery> deliveries;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
