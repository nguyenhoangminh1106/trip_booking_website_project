use travel_ticket_booking_web;

SELECT * FROM travel_ticket_booking_web.bookings;
ALTER TABLE bookings AUTO_INCREMENT = 1;

delete from bookings where id > 0;


create table bookings (
	id int auto_increment primary key,
    tripName varchar(30),
    price double,
    noOfAdults int,
    noOfChildren int,
    booker varchar(30),
    bookingDate date,
    status boolean
);

INSERT INTO bookings (tripName, price, noOfAdults, noOfChildren, booker, bookingDate, status)
VALUES
('Trip to Paris', 1200.00, 2, 1, 'Alice Nguyen', '2023-12-15', TRUE),
('Trip to Tokyo', 1500.00, 3, 0, 'Bob Tran', '2023-12-20', FALSE),
('Trip to London', 1800.00, 1, 2, 'Charlie Le', '2023-12-25', TRUE),
('Trip to New York', 2000.00, 4, 1, 'David Pham', '2023-12-30', FALSE),
('Trip to Rome', 1600.00, 2, 2, 'Eve Vu', '2023-12-31', TRUE),
('Trip to Sydney', 1700.00, 3, 1, 'Frank Hoang', '2023-12-01', FALSE),
('Trip to Berlin', 1400.00, 1, 0, 'Grace Nguyen', '2023-12-02', TRUE),
('Trip to Beijing', 1300.00, 2, 0, 'Harry Tran', '2023-12-03', FALSE),
('Trip to Seoul', 1100.00, 0, 1, 'Ivy Le', '2023-12-04', TRUE),
('Trip to Bangkok', 900.00, 1, 1, 'Jack Pham', '2023-12-05', FALSE),
('Trip to Cairo', 1900.00, 2, 3, 'Kate Vu', '2023-12-06', TRUE),
('Trip to Moscow', 2100.00, 3, 2, 'Leo Hoang', '2023-12-07', FALSE),
('Trip to Rio', 2200.00, 4, 0, 'Mary Nguyen', '2023-12-08', TRUE),
('Trip to Oslo', 2300.00, 5, 1, 'Nick Tran', '2023-12-09', FALSE),
('Trip to Athens', 1500.00, 2, 1, 'Olivia Le', '2023-12-10', TRUE),
('Trip to Delhi', 1000.00, 1, 0, 'Peter Pham', '2023-12-11', FALSE),
('Trip to Amsterdam', 1700.00, 3, 2, 'Quinn Vu', '2023-12-12', TRUE),
('Trip to Istanbul', 1600.00, 2, 2, 'Ryan Hoang', '2023-12-13', FALSE),
('Trip to Havana', 1800.00, 4, 1, 'Sophia Nguyen', '2023-12-14', TRUE),
('Trip to Singapore', 1400.00, 1, 1, 'Tom Tran', '2023-12-16', FALSE);