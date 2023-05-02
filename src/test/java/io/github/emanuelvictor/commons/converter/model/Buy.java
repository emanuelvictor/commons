package io.github.emanuelvictor.commons.converter.model;

import java.math.BigDecimal;

public class Buy {

    private BigDecimal value;
    private Account account;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
