INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity") VALUES (1, 'Double Room', 'DBL', 2);
INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity") VALUES (2, 'Triple Room', 'TRL', 3);

INSERT INTO "ibe"."room" ("id", "room_number", "room_type_id") VALUES (1, 101, 1);
INSERT INTO "ibe"."room" ("id", "room_number", "room_type_id") VALUES (2, 102, 2);
INSERT INTO "ibe"."room" ("id", "room_number", "room_type_id") VALUES (3, 103, 1);
INSERT INTO "ibe"."room" ("id", "room_number", "room_type_id") VALUES (4, 201, 1);
INSERT INTO "ibe"."room" ("id", "room_number", "room_type_id") VALUES (5, 202, 2);

INSERT INTO "ibe"."guest_type" ("id", "name") VALUES (1, 'adult')
INSERT INTO "ibe"."guest_type" ("id", "name") VALUES (2, 'junior')
INSERT INTO "ibe"."guest_type" ("id", "name") VALUES (3, 'baby')

INSERT INTO "ibe"."preferred_room_conf" ("id", "room_type_id", "guest_type_id", "quantity") VALUES (1, 1, 1, 2)
INSERT INTO "ibe"."preferred_room_conf" ("id", "room_type_id", "guest_type_id", "quantity") VALUES (2, 2, 1, 2)
INSERT INTO "ibe"."preferred_room_conf" ("id", "room_type_id", "guest_type_id", "quantity") VALUES (3, 2, 2, 1)

INSERT INTO "ibe"."guest_type_fare" ("id", "start_date", "end_date", "price", "guest_type_id") VALUES (1, '2019-01-01', '2019-12-31', 50.00, 1)
INSERT INTO "ibe"."guest_type_fare" ("id", "start_date", "end_date", "price", "guest_type_id") VALUES (2, '2019-01-01', '2019-12-31', 30.00, 2)
