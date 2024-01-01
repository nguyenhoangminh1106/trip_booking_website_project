use travel_ticket_booking_web;

SELECT * FROM travel_ticket_booking_web.posts;
ALTER TABLE posts AUTO_INCREMENT = 1;


create table posts (
	id int auto_increment primary key,
    postName varchar(30),
    img_url varchar(100),
    desc_url varchar(100)
);