package com.ctw.workstation.teammember.dto;

import java.util.UUID;

public class TeamMemberResponse {
    private String name;
    private String teamName;
    private UUID ctwId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCtwId() {
        return ctwId;
    }

    public void setCtwId(UUID ctwId) {
        this.ctwId = ctwId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
