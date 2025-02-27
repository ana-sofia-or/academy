CREATE SEQUENCE team_seq START 1;
CREATE SEQUENCE team_member_seq START 1;
CREATE SEQUENCE rack_asset_seq START 1;
CREATE SEQUENCE rack_seq START 1;
CREATE SEQUENCE booking_seq START 1;


CREATE TABLE T_TEAM(
   id bigint PRIMARY KEY UNIQUE,
   name varchar(30),
   product varchar(30),
   created_at timestamp DEFAULT now(),
   modified_at timestamp,
   default_location varchar(10)
);

CREATE TABLE T_TEAM_MEMBER(
    id bigint PRIMARY KEY UNIQUE,
    team_id bigint REFERENCES T_TEAM(id),
    ctw_id uuid DEFAULT gen_random_uuid(),
    name varchar(30),
    created_at timestamp DEFAULT now(),
    modified_at timestamp
);

CREATE TABLE T_RACK (
    id bigint PRIMARY KEY UNIQUE,
    serial_number varchar(20) NOT NULL,
    team_id bigint REFERENCES T_TEAM(id),
    default_location varchar(10),
    created_at timestamp DEFAULT now(),
    status varchar(20) CONSTRAINT status_types CHECK(status IN ('AVAILABLE', 'BOOKED', 'UNAVAILABLE')),
    modified_at timestamp
);

CREATE TABLE T_RACK_ASSET (
    id bigint PRIMARY KEY UNIQUE,
    asset_tag varchar(10) NOT NULL ,
    rack_id bigint NOT NULL
);

CREATE TABLE T_BOOKING(
    id bigint PRIMARY KEY UNIQUE,
    rack_id bigint REFERENCES T_RACK(id),
    requester_id bigint REFERENCES T_TEAM_MEMBER(id),
    book_from timestamp,
    book_to timestamp,
    created_at timestamp DEFAULT now(),
    modified_at timestamp
);