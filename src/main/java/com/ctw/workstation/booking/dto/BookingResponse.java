package com.ctw.workstation.booking.dto;

import java.time.LocalDateTime;

public class BookingResponse {
    private LocalDateTime from;
    private LocalDateTime to;
    private String requesterName;
    private String rackSerialNumber;

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRackSerialNumber() {
        return rackSerialNumber;
    }

    public void setRackSerialNumber(String rackSerialNumber) {
        this.rackSerialNumber = rackSerialNumber;
    }
}
