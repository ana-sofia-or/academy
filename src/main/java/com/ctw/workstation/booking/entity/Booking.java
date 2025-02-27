package com.ctw.workstation.booking.entity;

import com.ctw.workstation.common.Entity;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "T_BOOKING")
public final class Booking extends Entity {
    @Id
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    private Long id;
    @Column(name = "book_from")
    private LocalDateTime from;

    @Column(name = "book_to")
    private LocalDateTime to;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private TeamMember requester;

    @ManyToOne
    @JoinColumn(name = "rack_id", referencedColumnName = "id")
    private Rack rack;


    //getters and setters

    public LocalDateTime getFrom() { return from; }

    public void setFrom(LocalDateTime from) { this.from = from; }

    public LocalDateTime getTo() { return to; }

    public void setTo(LocalDateTime to) { this.to = to; }

    public TeamMember getRequester() { return requester; }

    public void setRequester(TeamMember requester) { this.requester = requester; }

    public Rack getRack() { return rack; }

    public void setRack(Rack rack) { this.rack = rack; }

    //constructor
    public Booking() { super(); }

    public Booking(Builder builder) {
        super(builder.createdAt, builder.modifiedAt);
        this.from = builder.from;
        this.to = builder.to;
        this.requester = builder.requester;
        this.rack = builder.rack;
    }

    //builder
    public static class Builder {
        private LocalDateTime from;
        private LocalDateTime to;
        private TeamMember requester;
        private Rack rack;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Builder setFrom(LocalDateTime from) {
            this.from = from;
            return this;
        }
        public Builder setTo(LocalDateTime to) {
            this.to = to;
            return this;
        }
        public Builder setRequester(TeamMember requester) {
            this.requester = requester;
            return this;
        }
        public Builder setRack(Rack rack) {
            this.rack = rack;
            return this;
        }
        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
