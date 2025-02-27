package com.ctw.workstation.booking.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BookingRequest {
    @NotNull
    private LocalDateTime from;
    private LocalDateTime to;
    @NotNull
    private Long requesterId;
    @NotNull
    private Long rackId;

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

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

}
