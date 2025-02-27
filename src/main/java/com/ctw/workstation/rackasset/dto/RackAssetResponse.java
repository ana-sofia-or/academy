package com.ctw.workstation.rackasset.dto;

public class RackAssetResponse {
    private String assetTag;
    private String rackSerialNumber;

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getRackSerialNumber() {
        return rackSerialNumber;
    }

    public void setRackSerialNumber(String rackSerialNumber) {
        this.rackSerialNumber = rackSerialNumber;
    }
}
