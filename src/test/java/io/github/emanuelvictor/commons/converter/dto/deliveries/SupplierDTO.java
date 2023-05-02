package io.github.emanuelvictor.commons.converter.dto.deliveries;

public class SupplierDTO {
    private String name;
    private String email;
    private RequirementDTO requirement;

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

    public RequirementDTO getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementDTO requirement) {
        this.requirement = requirement;
    }
}
