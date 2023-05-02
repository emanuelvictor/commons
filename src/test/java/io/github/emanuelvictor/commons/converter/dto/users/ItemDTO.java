package io.github.emanuelvictor.commons.converter.dto.users;

import java.math.BigDecimal;

public class ItemDTO {
    private BigDecimal value;
    private int count;
    private UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "value=" + value +
                ", count=" + count +
                ", user=" + user +
                '}';
    }
}
