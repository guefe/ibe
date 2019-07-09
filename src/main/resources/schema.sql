create schema ibe;
create table ibe.room_type
(
    id           serial
        constraint room_type_pk
            primary key,
    name         varchar(32)   not null,
    code         char(3)       not null,
    capacity     int           not null default 0,
    quantity     int           not null default 1,
    adult_price  numeric(6, 2) not null default 0.00,
    junior_price numeric(6, 2) not null default 0.00,
    baby_price   numeric(6, 2) not null default 0.00

);

create table ibe.standard_occupancy
(
    id           serial
        constraint standard_occupancy_pk
            primary key,
    room_type_id int not null
        constraint standard_occupancy_room_type_id_fk
            references ibe.room_type,
    adults       int not null default 0,
    juniors      int not null default 0,
    babies       int not null default 0
);

create table ibe.reservation
(
    id                 serial
        constraint reservation_pk
            primary key,
    reference          varchar(8) not null,
    total_amount       decimal(8,2),
    customer_full_name varchar(256),
    customer_email     varchar(256),
    start_date         date default CURRENT_DATE not null,
    end_date           date default CURRENT_DATE not null
);

create table ibe.reservation_room
(
    id             serial
        constraint reservation_room_pk
            primary key,
    amount         numeric(8,2) not null,
    adults         int          not null default 0,
    juniors        int          not null default 0,
    babies         int          not null default 0,
    room_type_id   int          not null
        constraint reservation_room_room_type_id_fk
            references ibe.room_type,
    reservation_id int          not null
        constraint reservation_room_reservation_id_fk
            references ibe.reservation
);

create table ibe.reservation_room_occupancy
(
    id                  serial
        constraint reservation_room_occupancy_pk
            primary key,
    adults              int not null default 0,
    juniors             int not null default 0,
    babies              int not null default 0,
    reservation_room_id int not null
        constraint reservation_room_occupancy_reservation_room_id_fk
            references ibe.reservation_room
);

