package com.ctw.workstation.rack.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public class RackRequest {

    private String defaultLocation;
    @NotNull
    private String serialNumber;
    private String status;
    private Long teamId;

    public String getDefaultLocation() { return defaultLocation; }

    public void setDefaultLocation(String defaultLocation) { this.defaultLocation = defaultLocation; }

    public String getSerialNumber() { return serialNumber; }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Optional<Long> getTeamId() {
        return Optional.ofNullable(teamId);
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public RackRequest(String defaultLocation, String serialNumber, String status, Long teamId) {
        this.defaultLocation = defaultLocation;
        this.serialNumber = serialNumber;
        this.status = status;
        this.teamId = teamId;
    }
}
