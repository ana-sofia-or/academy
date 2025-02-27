package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.dto.RackRequest;
import com.ctw.workstation.rack.dto.RackResponse;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.services.RackService;
import com.ctw.workstation.services.TeamService;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Path("/workstation/racks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RackResource {

    @Inject
    RackService rackService;
    @Inject
    TeamService teamService;

    @GET
    public Response get() {
        List<RackResponse> racksList = rackService.getAllRacks();

        return Response.ok().entity(racksList).build();
    }

    @GET
    @Path("/racks")
    public Response get(@QueryParam("status") String status) {
        List<Rack> racksList = rackService.getFilteredRacks(status);
        return Response.ok().entity(racksList).build();
    }

    @GET
    @Path("/{id}")
    public Response get(Long id) {
        Rack rack = rackService.getRack(id);

        RackResponse rackResponse = new RackResponse();
        rackResponse.setSerialNumber(rack.getSerialNumber());
        rackResponse.setStatus(rack.getStatus());
        rackResponse.setDefaultLocation(rack.getDefaultLocation());
        if(rack.getTeam() != null) {
            rackResponse.setTeamName(rack.getTeam().getName());
        }
        return Response.ok().entity(rackResponse).build();
    }

    @POST
    public Response create(@RequestBody  @Valid RackRequest rackRequest) {
        RackResponse rackResponse = rackService.createRack(rackRequest);

        return Response.status(Response.Status.CREATED).entity(rackResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody RackRequest rackRequest, Long id) {
        RackResponse rackResponse = rackService.updateRack(rackRequest, id);

        return Response.ok().entity(rackResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        Rack rack = rackService.getRack(id);
        rackService.removeRack(rack);
        return Response.ok().build();
    }

}
