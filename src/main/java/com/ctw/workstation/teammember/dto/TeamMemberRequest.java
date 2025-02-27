package com.ctw.workstation.teammember.dto;

import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class TeamMemberRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long teamId;
    private UUID ctwId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public UUID getCtwId() {
        return ctwId;
    }

    public void setCtwId(UUID ctwId) {
        this.ctwId = ctwId;
    }
}
