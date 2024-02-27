-- TABLE for events
CREATE TABLE IF NOT EXISTS events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    location VARCHAR(255),
    start_timestamp BIGINT,
    end_timestamp BIGINT
    );

-- EVENTS
INSERT INTO events (title, location, start_timestamp, end_timestamp) VALUES ('Szilveszteri zártkörű rendezvény', 'Greenwich', 1577836800, 1577844000);
INSERT INTO events (title, location, start_timestamp, end_timestamp) VALUES ('Májusi mulatság', 'Budapest', 1588334400, 1588348800);
INSERT INTO events (title, location, start_timestamp, end_timestamp) VALUES ('Necc party', 'Debrecen', 1607731200, 1607817599);

-- TABLE for seats
CREATE TABLE seats (
    id SERIAL PRIMARY KEY,
    seat_name VARCHAR(255) NOT NULL,
    event_id BIGINT NOT NULL,
    price INT NOT NULL,
    currency VARCHAR(3) NOT NULL,
    reserved BOOLEAN NOT NULL,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events (id)
);

-- EVENT SEATS
-- Event 1 seats
INSERT INTO seats (seat_name, event_id, price, currency, reserved) VALUES
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
INSERT INTO seats (seat_name, event_id, price, currency, reserved) VALUES
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
INSERT INTO seats (seat_name, event_id, price, currency, reserved) VALUES
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

-- TABLE for reservations
CREATE TABLE IF NOT EXISTS reservations (
    id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    seat_id INT NOT NULL,
    CONSTRAINT fkey_event FOREIGN KEY (event_id) REFERENCES events (id),
    CONSTRAINT fkey_seat FOREIGN KEY (seat_id) REFERENCES seats (id)
    );

