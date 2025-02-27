package com.ctw.workstation.team.boundary;

import com.ctw.workstation.services.TeamService;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.dto.TeamMemberResponse;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

@Path("/workstation/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamService teamService;

    public TeamResource() {

    }

    @GET
    public Response get() {
        List<TeamResponse> teamResponseList = teamService.getTeams();

        return Response.ok().entity(teamResponseList).build();
    }


    @GET
    @Path("/{id}")
    public Response get(Long id) {
        Team team = teamService.getTeam(id);
        if (team == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        TeamResponse teamResponse = convertTeamToTeamResponse(team);
        return Response.ok().entity(teamResponse).build();
    }

    @POST
    public Response create(@Valid TeamRequest teamRequest) {
        TeamResponse teamResponse = teamService.createTeam(teamRequest);

        return Response.status(Response.Status.CREATED).entity(teamResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody TeamRequest teamRequest, Long id) {
        Team team = teamService.updateTeam(teamRequest, id);
        TeamResponse teamResponse = convertTeamToTeamResponse(team);

        return Response.ok().entity(teamResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        Team team = teamService.getTeam(id);
        teamService.removeTeam(team);
        return Response.ok().build();
    }

    private TeamResponse convertTeamToTeamResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setName(team.getName());
        teamResponse.setDefaultLocation(team.getDefaultLocation());
        teamResponse.setProduct(team.getProduct());

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