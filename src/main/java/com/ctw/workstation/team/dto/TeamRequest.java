package com.ctw.workstation.team.dto;

import jakarta.validation.constraints.NotBlank;

public class TeamRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String product;
    private String defaultLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }
    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public TeamRequest(String name, String product, String defaultLocation) {
        this.name = name;
        this.product = product;
        this.defaultLocation = defaultLocation;
    }

    public TeamRequest() {}
}
