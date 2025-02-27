package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset extends com.ctw.workstation.common.Entity {
    @Id
    @SequenceGenerator(name = "rack_asset_seq", sequenceName = "rack_asset_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_asset_seq")
    private Long id;

    @Column(name = "asset_tag", length = 10, nullable = false)
    private String assetTag;

    @ManyToOne
    @JoinColumn(name = "rack_id", referencedColumnName = "id", nullable = false)
    private Rack rack; // Assuming Rack is another entity

    // Getters and setters
    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    // Constructor
    public RackAsset(LocalDateTime createdAt, LocalDateTime modifiedAt, String assetTag, Rack rack) {
        super(createdAt, modifiedAt);
        this.assetTag = assetTag;
        this.rack = rack;
    }

    // Default constructor
    public RackAsset() {
        super();
    }

    // Builder pattern for creating RackAsset objects
    public static class Builder {
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String assetTag;
        private Rack rack;

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setAssetTag(String assetTag) {
            this.assetTag = assetTag;
            return this;
        }

        public Builder setRack(Rack rack) {
            this.rack = rack;
            return this;
        }

        public RackAsset build() {
            return new RackAsset(createdAt, modifiedAt, assetTag, rack);
        }
    }
}
