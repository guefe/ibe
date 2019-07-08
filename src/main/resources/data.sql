INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity", "quantity", "adult_price", "junior_price", "baby_price") VALUES (1, 'Double Room', 'DBL', 2, 3, 50.00, 30.00, 0.00);
INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity", "quantity", "adult_price", "junior_price", "baby_price") VALUES (2, 'Triple Room', 'TRL', 3, 2, 50.00, 30.00, 0.00);

INSERT INTO "ibe"."standard_occupancy" ("id", "room_type_id", "adults", "juniors", "babies") VALUES (1, 1, 2, 0, 0);
INSERT INTO "ibe"."standard_occupancy" ("id", "room_type_id", "adults", "juniors", "babies") VALUES (2, 2, 2, 1, 0);

