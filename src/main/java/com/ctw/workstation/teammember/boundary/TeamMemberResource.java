package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.services.TeamMemberService;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.teammember.dto.TeamMemberRequest;
import com.ctw.workstation.teammember.dto.TeamMemberResponse;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/workstation/team-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMemberResource {

    @Inject
    TeamMemberService teamMemberService;

    public TeamMemberResource() {

    }

    @GET
    public Response get() {
        List<TeamMember> teamMembersList = teamMemberService.getTeamMembers();
        return Response.ok().entity(teamMembersList).build();
    }


    @GET
    @Path("/{id}")
    public Response get(Long id) {
        TeamMember teamMember = teamMemberService.getTeamMember(id);
        return Response.ok().entity(teamMember).build();
    }

    @POST
    public Response create(TeamMemberRequest teamMemberRequest) {
        TeamMember teamMember = teamMemberService.createTeamMember(teamMemberRequest);

        TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
        teamMemberResponse.setCtwId(teamMember.getCtwId());
        teamMemberResponse.setTeamName(teamMember.getName());
        teamMemberResponse.setName(teamMember.getName());

        return Response.status(Response.Status.CREATED).entity(teamMemberResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody TeamMemberRequest teamMemberRequest, Long id) {
        TeamMember teamMember = teamMemberService.updateTeamMember(teamMemberRequest, id);

        TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
        teamMemberResponse.setCtwId(teamMember.getCtwId());
        teamMemberResponse.setTeamName(teamMember.getName());
        teamMemberResponse.setName(teamMemberRequest.getName());

        return Response.ok().entity(teamMemberResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        TeamMember teamMember = teamMemberService.getTeamMember(id);
        teamMemberService.removeTeamMember(teamMember);
        return Response.ok().build();
    }
}
