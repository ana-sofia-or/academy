package com.ctw.workstation.rackasset.boundary;

import com.ctw.workstation.rackasset.dto.RackAssetRequest;
import com.ctw.workstation.rackasset.dto.RackAssetResponse;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.services.RackAssetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/workstation/rack-assets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RackAssetResource {

    @Inject
    RackAssetService rackAssetService;

    public RackAssetResource() {

    }

    @GET
    public Response get() {
        List<RackAsset> rackAssetsList = rackAssetService.getRackAssets();
        return Response.ok().entity(rackAssetsList).build();
    }


    @GET
    @Path("/{id}")
    public Response get(Long id) {
        RackAsset rackAsset = rackAssetService.getRackAsset(id);

        RackAssetResponse rackAssetResponse = new RackAssetResponse();
        rackAssetResponse.setAssetTag(rackAsset.getAssetTag());
        rackAssetResponse.setRackSerialNumber(rackAsset.getRack().getSerialNumber());

        return Response.ok().entity(rackAssetResponse).build();
    }

    @POST
    public Response create(RackAssetRequest rackAssetRequest) {
        RackAsset rackAsset = rackAssetService.createRackAsset(rackAssetRequest);

        RackAssetResponse rackAssetResponse = new RackAssetResponse();
        rackAssetResponse.setAssetTag(rackAsset.getAssetTag());
        rackAssetResponse.setRackSerialNumber(rackAsset.getRack().getSerialNumber());

        return Response.status(Response.Status.CREATED).entity(rackAssetResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody RackAssetRequest rackAssetRequest, Long id) {
        RackAsset rackAsset = rackAssetService.updateRackAsset(rackAssetRequest, id);

        RackAssetResponse rackAssetResponse = new RackAssetResponse();
        rackAssetResponse.setAssetTag(rackAsset.getAssetTag());
        rackAssetResponse.setRackSerialNumber(rackAsset.getRack().getSerialNumber());

        return Response.ok().entity(rackAssetResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        RackAsset rackAsset = rackAssetService.getRackAsset(id);
        rackAssetService.removeRackAsset(rackAsset);
        return Response.ok().build();
    }
}
