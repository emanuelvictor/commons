package io.github.emanuelvictor.commons.converter.dto.accounts.list;

import java.math.BigDecimal;

public class BuyDTO {

    private BigDecimal value;
    private AccountDTO account;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
