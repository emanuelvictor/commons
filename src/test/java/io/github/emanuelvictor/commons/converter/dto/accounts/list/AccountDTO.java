package io.github.emanuelvictor.commons.converter.dto.accounts.list;

import java.util.List;

public class AccountDTO {

    private String name;
    private String email;
    private List<BuyDTO> buys;

    public List<BuyDTO> getBuys() {
        return buys;
    }

    public void setBuys(List<BuyDTO> buys) {
        this.buys = buys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
