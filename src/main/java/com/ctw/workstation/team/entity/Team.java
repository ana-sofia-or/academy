package com.ctw.workstation.team.entity;

import com.ctw.workstation.common.Entity;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="T_TEAM")
public final class Team extends Entity {
    @Id
    @SequenceGenerator(name = "team_seq", sequenceName = "team_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    private Long id;
    @Column(length = 30)
    private String name;
    @Column(length = 30)
    private String product;
    @Column(name = "default_location", length = 10)
    private String defaultLocation;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<TeamMember> teamMemberList = new ArrayList<>();
    //getters & setters
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public List<TeamMember> getTeamMemberList() {
        return teamMemberList;
    }

    public void setTeamMemberList(List<TeamMember> teamMemberList) {
        this.teamMemberList = teamMemberList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Convenience method for adding/removing members (optional)
    public void addTeamMember(TeamMember member) {
        this.teamMemberList.add(member);
        member.setTeam(this);
    }

    public void removeTeamMember(TeamMember member) {
        this.teamMemberList.remove(member);
        member.setTeam(null);
    }

    //constructor
    public Team(LocalDateTime createdAt, LocalDateTime modifiedAt, String name , String product, String defaultLocation) {
        super(createdAt, modifiedAt);
        this.name = name;
        this.product = product;
        this.defaultLocation = defaultLocation;
    }

    public Team() {
        super();
    }

    //builder
    public static class Builder {
        private String name;
        private String product;
        private String defaultLocation;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setProduct(String product) {
            this.product = product;
            return this;
        }

        public Builder setDefaultLocation(String defaultLocation) {
            this.defaultLocation = defaultLocation;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Team build() {
            return new Team(createdAt, modifiedAt, name, product, defaultLocation);
        }
    }

}
