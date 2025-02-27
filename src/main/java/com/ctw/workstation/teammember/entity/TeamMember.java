package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember extends com.ctw.workstation.common.Entity {
    @Id
    @SequenceGenerator(name = "team_member_seq", sequenceName = "team_member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_member_seq")
    private Long id;
    @Column(name = "name", length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @Column(name = "ctw_id")
    private UUID ctwId;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public UUID getCtwId() {
        return ctwId;
    }

    public void setCtwId(UUID ctwId) {
        this.ctwId = ctwId;
    }

    // Constructor
    public TeamMember(LocalDateTime createdAt, LocalDateTime modifiedAt, String name, UUID ctwId, Team team) {
        super(createdAt, modifiedAt);
        this.name = name;
        this.ctwId = ctwId;
        this.team = team;
    }

    public TeamMember() {
        super();
    }

    public static class Builder {
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String name;
        private UUID ctwId;
        private Team team;

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setCtwId(UUID ctwId) {
            this.ctwId = ctwId;
            return this;
        }
        public Builder setTeam(Team team) {
            this.team = team;
            return this;
        }
        public TeamMember build() {
            return new TeamMember(createdAt, modifiedAt, name, ctwId, team);
        }
    }
}
