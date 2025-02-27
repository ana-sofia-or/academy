package com.ctw.workstation.data;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public final class BookingRepository implements PanacheRepository<Booking> {

    public BookingRepository() {}

    public List<Booking> findBookingsByRackAndTime(Rack rack, LocalDateTime from, LocalDateTime to) {
        return find("rack = ?1 and ((from >= ?2 and from < ?3) or (to > ?2 and to <= ?3))",
                rack, from, to).list();
    }
}
