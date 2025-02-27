package com.ctw.workstation.services;

import com.ctw.workstation.data.TeamRepository;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.dto.TeamMemberResponse;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TeamService {
    @Inject
    TeamRepository repository;

    private TeamService() {

    }

    @Transactional
    public List<TeamResponse> getTeams(){
        //MDC.put("request.id", 1);
        //MDC.put("request.path", "/hello/test");
        Log.info("get teams");
        List<Team> teamsList = repository.listAll();

        List<TeamResponse> teamResponseList = new ArrayList<>();
        for (Team team : teamsList) {
            TeamResponse teamResponse = convertTeamToTeamResponse(team);
            teamResponseList.add(teamResponse);
        }
        return teamResponseList;
    }

    @Transactional
    public Team getTeam(Long id) {
//        Log.infof("Fetching team by id: " + id);
//        Log.infov("Fetching team by id: " + id);
        return repository.findById(id);
    }

    @Transactional
    public TeamResponse createTeam(TeamRequest teamRequest) {
        Team team = new Team.Builder().setName(teamRequest.getName())
                .setDefaultLocation(teamRequest.getDefaultLocation())
                .setProduct(teamRequest.getProduct())
                .build();

        repository.persistAndFlush(team);
        System.out.println(team.getId());
        TeamResponse teamResponse = convertTeamToTeamResponse(team);
        Log.info("Team created: " + team);
        return teamResponse;
    }

    @Transactional
    public Team updateTeam(TeamRequest teamRequest, Long id) {
        Team team = repository.findById(id);
        team.setName(teamRequest.getName());
        team.setProduct(teamRequest.getProduct());

        repository.persistAndFlush(team);
        return team;
    }

    @Transactional
    public void removeTeam(Team team) {
        repository.delete(team);
    }


    private TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setName(team.getName());
        teamResponse.setDefaultLocation(team.getDefaultLocation());
        teamResponse.setProduct(team.getProduct());
        teamResponse.setId(team.getId());

        List<TeamMemberResponse> teamMemberResponses = new ArrayList<>();
        for (TeamMember teamMember : team.getTeamMemberList()) {
            TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
            teamMemberResponse.setTeamName(teamMember.getName());
            teamMemberResponse.setName(teamMember.getName());
            teamMemberResponse.setCtwId(teamMember.getCtwId());
            teamMemberResponses.add(teamMemberResponse);
        }

        teamResponse.setMemberList(teamMemberResponses);

        return teamResponse;
    }
}
