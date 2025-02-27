package com.ctw.workstation.rack.entity;

import com.ctw.workstation.common.Entity;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@jakarta.persistence.Entity
@Table(name = "T_RACK")
public final class Rack extends Entity {
    @Id
    @SequenceGenerator(name = "rack_seq", sequenceName = "rack_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_seq")
    private Long id;
    @Column(name = "serial_number", nullable = false, length = 20)
    private String serialNumber;
    @Column(name = "default_location", length = 10)
    private String defaultLocation;
    @Column(name = "status", length = 20)
    private String status;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    //getters & setters
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDefaultLocation() { return defaultLocation; }

    public void setDefaultLocation(String defaultLocation) { this.defaultLocation = defaultLocation; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //constructor
    public Rack() {}

    public Rack(Builder builder) {
        super(builder.createdAt, builder.modifiedAt);
        this.serialNumber = builder.serialNumber;
        this.defaultLocation = builder.defaultLocation;
        this.status = builder.status;
        this.team = builder.team;
    }

    //builder
    public static class Builder {
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String serialNumber;
        private String defaultLocation;
        private String status;
        private Team team;

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }
        public Builder setDefaultLocation(String defaultLocation) {
            this.defaultLocation = defaultLocation;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }
        public Builder setTeam(Team team) {
            this.team = team;
            return this;
        }
        public Rack build() {
            return new Rack(this);
        }
    }
}



