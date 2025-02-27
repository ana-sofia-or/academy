package com.ctw.workstation.services;

import com.ctw.workstation.booking.dto.BookingRequest;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.data.BookingRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository repository;
    @Inject
    TeamMemberService teamMemberService;
    @Inject
    RackService rackService;

    private BookingService() { }

    @Transactional
    public List<Booking> getBookings(){
        return repository.listAll();
    }

    @Transactional
    public Booking getBooking(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {
        TeamMember teamMember = teamMemberService.getTeamMember(bookingRequest.getRequesterId());
        Rack rack = rackService.getRack(bookingRequest.getRackId());

        if (rack.getStatus().equals("UNAVAILABLE")) {
            throw new IllegalStateException("The rack is unavailable.");
        }
        List<Booking> overlappingBookings = repository.findBookingsByRackAndTime(rack, bookingRequest.getFrom(), bookingRequest.getTo());
        if (!overlappingBookings.isEmpty()) {
            throw new IllegalStateException("The rack is already booked during this time period.");
        }

        Booking newBooking = new Booking.Builder()
                .setFrom(bookingRequest.getFrom())
                .setTo(bookingRequest.getTo())
                .setRequester(teamMember)
                .setRack(rack).build();

        repository.persistAndFlush(newBooking);
        return newBooking;
    }

    @Transactional
    public Booking updateBooking(BookingRequest bookingRequest, Long id) {
        TeamMember teamMember = teamMemberService.getTeamMember(bookingRequest.getRequesterId());
        Rack rack = rackService.getRack(bookingRequest.getRackId());

        List<Booking> overlappingBookings = repository.findBookingsByRackAndTime(rack, bookingRequest.getFrom(), bookingRequest.getTo());

        if (!overlappingBookings.isEmpty()) {
            throw new IllegalStateException("The rack is already booked during this time period.");
        }

        Booking booking = repository.findById(id);
        booking.setFrom(bookingRequest.getFrom());
        booking.setTo(bookingRequest.getTo());
        booking.setRequester(teamMember);
        booking.setRack(rack);

        repository.persistAndFlush(booking);
        return booking;
    }

    @Transactional
    public void removeBooking(Booking booking) {
        repository.delete(booking);
    }

}
