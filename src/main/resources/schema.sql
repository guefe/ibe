create schema ibe;
create table ibe.guest_type
(
	id int
		constraint guest_type_pk
			primary key,
	name varchar(32) not null
);

create table ibe.guest_type_fare
(
	id int
		constraint guest_type_fare_pk
			primary key,
	start_date date not null,
	end_date date not null,
	price numeric(6,2) not null,
	guest_type_id int not null
		constraint guest_type_fare_guest_type_id_fk
			references ibe.guest_type
);

create table ibe.room_type
(
    id int
        constraint room_type_pk
            primary key,
    name varchar(32) not null,
    code char(3) not null,
    capacity int not null
);

create table ibe.preferred_room_conf
(
    id int
        constraint preferred_room_conf_pk
            primary key,
    room_type_id int not null
        constraint preferred_room_conf_room_type_id_fk
            references ibe.room_type,
    guest_type_id int not null
        constraint preferred_room_conf_guest_type_id_fk
            references ibe.guest_type,
    quantity int not null
);

create table ibe.room
(
    id int
        constraint room_pk
            primary key,
    room_number int not null,
    room_type_id int
        constraint room_room_type_id_fk
            references ibe.room_type
);

create table ibe.reservation
(
    id int
        constraint reservation_pk
            primary key,
    reference varchar(8) not null,
    total_amount decimal(8,2),
    customer_full_name varchar(256),
    customer_email varchar(256),
    start_date date default CURRENT_DATE not null,
    end_date date default CURRENT_DATE not null
);

create table ibe.reservation_room
(
    id int
        constraint reservation_room_pk
            primary key,
    amount numeric(8,2) not null,
    room_id int not null
        constraint reservation_room_room_id_fk
            references ibe.room,
    reservation_id int not null
        constraint reservation_room_reservation_id_fk
            references ibe.reservation
);

create table ibe.reservation_guest
(
    id int
        constraint reservation_guest_pk
            primary key,
    guest_id_number varchar(16) not null,
    amount numeric(6, 2),
    guest_type_id int not null
        constraint reservation_guest_guest_type_id_fk
            references ibe.guest_type,
    reservation_room_id int not null
        constraint reservation_guest_reservation_room_id_fk
            references ibe.reservation_room
);

