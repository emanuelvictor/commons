package io.github.emanuelvictor.commons.converter.dto;

import java.util.Set;

public class AccountDTO {

    private String name;
    private String email;
    private Set<BuyDTO> buys;

    public Set<BuyDTO> getBuys() {
        return buys;
    }

    public void setBuys(Set<BuyDTO> buys) {
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
