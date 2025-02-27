package com.ctw.workstation.team.dto;

import com.ctw.workstation.teammember.dto.TeamMemberResponse;

import java.util.ArrayList;
import java.util.List;

public class TeamResponse {
    private Long id;
    private String name;
    private String product;
    private String defaultLocation;
    private List<TeamMemberResponse> memberList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }
    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public List<TeamMemberResponse> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<TeamMemberResponse> memberList) {
        this.memberList = memberList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamResponse(Long id, String name, String defaultLocation, String product, List<TeamMemberResponse> memberList) {
        this.id = id;
        this.name = name;
        this.defaultLocation = defaultLocation;
        this.product = product;
        this.memberList = memberList;
    }

    public TeamResponse() {}
}
