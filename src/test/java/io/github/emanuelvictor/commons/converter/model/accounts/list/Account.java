package io.github.emanuelvictor.commons.converter.model.accounts.list;

import java.util.List;

public class Account {

    private String name;
    private String email;
    private List<Buy> buys;

    public List<Buy> getBuys() {
        return buys;
    }

    public void setBuys(List<Buy> buys) {
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
