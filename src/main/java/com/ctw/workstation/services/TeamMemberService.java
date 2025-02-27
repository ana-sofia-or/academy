package com.ctw.workstation.services;

import com.ctw.workstation.data.TeamMemberRepository;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.dto.TeamMemberRequest;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TeamMemberService {

    @Inject
    TeamMemberRepository repository;
    @Inject
    TeamService teamService;

    private TeamMemberService() {

    }

    @Transactional
    public List<TeamMember> getTeamMembers(){
        return repository.listAll();
    }

    @Transactional
    public TeamMember getTeamMember(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public TeamMember createTeamMember(TeamMemberRequest teamMemberRequest) {
        Team team = teamService.getTeam(teamMemberRequest.getTeamId());
        TeamMember teamMember = new TeamMember.Builder().setName(teamMemberRequest.getName())
                .setCtwId(teamMemberRequest.getCtwId())
                .setTeam(team).build();

        repository.persistAndFlush(teamMember);
        return teamMember;
    }

    @Transactional
    public TeamMember updateTeamMember(TeamMemberRequest teamMemberRequest, Long id) {
        Team team = teamService.getTeam(teamMemberRequest.getTeamId());

        TeamMember teamMember = getTeamMember(id);
        teamMember.setName(teamMemberRequest.getName());
        teamMember.setTeam(team);
        teamMember.setCtwId(teamMemberRequest.getCtwId());

        repository.persistAndFlush(teamMember);
        return teamMember;
    }

    @Transactional
    public void removeTeamMember(TeamMember teamMember) {
        repository.delete(teamMember);
    }
}
