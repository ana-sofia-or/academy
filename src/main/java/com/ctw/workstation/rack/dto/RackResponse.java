package com.ctw.workstation.rack.dto;

public class RackResponse {
    private String defaultLocation;
    private String serialNumber;
    private String status;
    private String teamName;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public RackResponse(String serialNumber, String defaultLocation, String status, String teamName) {
        this.serialNumber = serialNumber;
        this.defaultLocation = defaultLocation;
        this.status = status;
        this.teamName = teamName;
    }

    public RackResponse() {}
}
