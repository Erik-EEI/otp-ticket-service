-- EVENTS
INSERT INTO event (title, location, start_timestamp, end_timestamp) VALUES ('Szilveszteri zártkörű rendezvény', 'Greenwich', 1577836800, 1577844000);
INSERT INTO event (title, location, start_timestamp, end_timestamp) VALUES ('Májusi mulatság', 'Budapest', 1588334400, 1588348800);
INSERT INTO event (title, location, start_timestamp, end_timestamp) VALUES ('Necc party', 'Debrecen', 1607731200, 1607817599);

-- EVENT SEATS
-- Event 1 seats
INSERT INTO seat (seat_name, event_id, price, currency, reserved) VALUES
                                                                       ('S1', 1, 1000, 'HUF', TRUE),
                                                                       ('S2', 1, 1000, 'HUF', FALSE),
                                                                       ('S3', 1, 1000, 'HUF', FALSE),
                                                                       ('S4', 1, 1000, 'HUF', FALSE),
                                                                       ('S5', 1, 1000, 'HUF', FALSE),
                                                                       ('S6', 1, 1000, 'HUF', TRUE),
                                                                       ('S7', 1, 1000, 'HUF', TRUE),
                                                                       ('S8', 1, 1000, 'HUF', TRUE),
                                                                       ('S9', 1, 1000, 'HUF', TRUE),
                                                                       ('S10', 1, 1000, 'HUF', TRUE);

-- Event 2 seats
INSERT INTO seat (seat_name, event_id, price, currency, reserved) VALUES
                                                                       ('S1', 2, 2000, 'HUF', FALSE),
                                                                       ('S2', 2, 2000, 'HUF', TRUE),
                                                                       ('S3', 2, 2000, 'HUF', FALSE),
                                                                       ('S4', 2, 2000, 'HUF', FALSE),
                                                                       ('S5', 2, 2000, 'HUF', FALSE),
                                                                       ('S6', 2, 2000, 'HUF', TRUE),
                                                                       ('S7', 2, 2000, 'HUF', TRUE),
                                                                       ('S8', 2, 2000, 'HUF', TRUE),
                                                                       ('S9', 2, 2000, 'HUF', TRUE),
                                                                       ('S10', 2, 2000, 'HUF', TRUE);

-- Event 3 seats
INSERT INTO seat (seat_name, event_id, price, currency, reserved) VALUES
                                                                       ('S1', 3, 3000, 'HUF', FALSE),
                                                                       ('S2', 3, 3000, 'HUF', FALSE),
                                                                       ('S3', 3, 3000, 'HUF', TRUE),
                                                                       ('S4', 3, 3000, 'HUF', FALSE),
                                                                       ('S5', 3, 3000, 'HUF', FALSE),
                                                                       ('S6', 3, 3000, 'HUF', TRUE),
                                                                       ('S7', 3, 3000, 'HUF', TRUE),
                                                                       ('S8', 3, 3000, 'HUF', TRUE),
                                                                       ('S9', 3, 3000, 'HUF', TRUE),
                                                                       ('S10', 3, 3000, 'HUF', TRUE);
