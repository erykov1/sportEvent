CREATE TABLE reports (
    report_id UUID PRIMARY KEY,
    username CHARACTER VARYING(255),
    report_status CHARACTER VARYING(255),
    reported_at TIMESTAMP(6) with time zone,
    sport_event_id BIGINT
);

CREATE TABLE sport_events_assign (
    sport_event_id BIGINT PRIMARY KEY,
    max_participants BIGINT,
    registration_deadline TIMESTAMP(6) with time zone,
    event_time TIMESTAMP(6) with time zone
);

CREATE TABLE sport_events (
    sport_event_id BIGINT PRIMARY KEY,
    event_name CHARACTER VARYING(255),
    event_time TIMESTAMP(6) with time zone,
    registration_deadline TIMESTAMP(6) with time zone,
    description CHARACTER VARYING(255),
    max_participants BIGINT,
    sport_event_type CHARACTER VARYING(255),
    sport_event_address BIGINT
);

CREATE TABLE sport_events_addresses (
    event_address_id BIGINT PRIMARY KEY,
    postal_code CHARACTER VARYING(255),
    city CHARACTER VARYING(255),
    street CHARACTER VARYING(255),
    street_number CHARACTER VARYING(255)
);

CREATE TABLE users (
    user_id BIGINT PRIMARY KEY,
    username CHARACTER VARYING(255),
    password CHARACTER VARYING(255),
    user_role CHARACTER VARYING(255)
);