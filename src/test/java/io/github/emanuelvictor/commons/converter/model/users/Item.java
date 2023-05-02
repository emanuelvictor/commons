package io.github.emanuelvictor.commons.converter.model.users;

import java.math.BigDecimal;

public class Item {
    private BigDecimal value;
    private int count;
    private User user;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
