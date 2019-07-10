INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity", "quantity", "adult_price", "junior_price", "baby_price") VALUES (1, 'Double Room', 'DBL', 2, 3, 50.00, 30.00, 0.00);
INSERT INTO "ibe"."room_type" ("id", "name", "code", "capacity", "quantity", "adult_price", "junior_price", "baby_price") VALUES (2, 'Triple Room', 'TRL', 3, 2, 50.00, 30.00, 0.00);

INSERT INTO "ibe"."standard_occupancy" ("id", "room_type_id", "adults", "juniors", "babies") VALUES (1, 1, 2, 0, 0);
INSERT INTO "ibe"."standard_occupancy" ("id", "room_type_id", "adults", "juniors", "babies") VALUES (2, 2, 2, 1, 0);

INSERT INTO "ibe"."availability_restriction" ("id", "type", "start_date", "end_date", "day_of_week", "min_days") VALUES (1, 'CHECK_IN_DATE', '2019-08-01', '2019-08-31', 3, 2);
INSERT INTO "ibe"."availability_restriction" ("id", "type", "start_date", "end_date", "day_of_week", "min_days") VALUES (2, 'CHECK_OUT_DATE', '2019-08-01', '2019-08-31', 5, null);