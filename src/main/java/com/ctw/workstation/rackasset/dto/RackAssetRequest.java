package com.ctw.workstation.rackasset.dto;

public class RackAssetRequest {
    private String assetTag;
    private Long rackId;

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }
}
