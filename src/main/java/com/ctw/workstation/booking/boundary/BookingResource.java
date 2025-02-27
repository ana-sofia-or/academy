package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.dto.BookingRequest;
import com.ctw.workstation.booking.dto.BookingResponse;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.services.BookingService;
import com.ctw.workstation.services.RackService;
import com.ctw.workstation.services.TeamMemberService;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Path("/workstation/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService bookingService;
    @Inject
    RackService rackService;
    @Inject
    TeamMemberService teamMemberService;

    @GET
    public Response get() {
        List<Booking> bookingsList = bookingService.getBookings();
        return Response.ok().entity(bookingsList).build();
    }

    @GET
    @Path("/{id}")
    public Response get(Long id) {
        Booking booking = bookingService.getBooking(id);
        return Response.ok().entity(booking).build();
    }

    @POST
    public Response create(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(bookingRequest);

            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setFrom(booking.getFrom());
            bookingResponse.setTo(booking.getTo());
            bookingResponse.setRequesterName(booking.getRequester().getName());
            bookingResponse.setRackSerialNumber(booking.getRack().getSerialNumber());

            return Response.status(Response.Status.CREATED).entity(bookingResponse).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: " + e.getMessage())
                    .build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody BookingRequest bookingRequest, Long id) {
        try {
            Booking booking = bookingService.updateBooking(bookingRequest, id);

            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setFrom(booking.getFrom());
            bookingResponse.setTo(booking.getTo());
            bookingResponse.setRequesterName(booking.getRequester().getName());
            bookingResponse.setRackSerialNumber(booking.getRack().getSerialNumber());

            return Response.ok().entity(bookingResponse).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        Booking booking = bookingService.getBooking(id);
        bookingService.removeBooking(booking);
        return Response.ok().build();
    }
}