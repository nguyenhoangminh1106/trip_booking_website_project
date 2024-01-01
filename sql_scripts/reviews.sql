use travel_ticket_booking_web;

SELECT * FROM travel_ticket_booking_web.reviews;
ALTER TABLE reviews AUTO_INCREMENT = 1;

alter table reviews add content varchar(1000);

drop table reviews;

create table reviews (
	id int auto_increment primary key,
    reviewer varchar(30),
	post_id int,
    rating int,
    reviewDate date,
    content varchar(1000),
    status boolean
);