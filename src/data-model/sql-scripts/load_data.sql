-- load approach shot line lookup data...
insert into approach_shot_line (id, locale_id, description, short_description) values (0, 0, 'Left', 'L');
insert into approach_shot_line (id, locale_id, description, short_description) values (1, 0, 'Green', 'G');
insert into approach_shot_line (id, locale_id, description, short_description) values (2, 0, 'Right', 'R');

-- load approach shot distance lookup data...
insert into approach_shot_distance (id, locale_id, description, short_description) values (0, 0, 'Short', 'S');
insert into approach_shot_distance (id, locale_id, description, short_description) values (1, 0, 'Pin high', 'PH');
insert into approach_shot_distance (id, locale_id, description, short_description) values (2, 0, 'Long', 'L');

-- load tee shot accuracy lookup data...
insert into tee_shot_accuracy (id, locale_id, description, short_description) values (0, 0, 'Left', 'L');
insert into tee_shot_accuracy (id, locale_id, description, short_description) values (1, 0, 'Fairway', 'F');
insert into tee_shot_accuracy (id, locale_id, description, short_description) values (2, 0, 'Right', 'R');

-- load green-in-regulation lookup data...
insert into green_in_regulation (id, locale_id, description, short_description) values (0, 0, '<= 15''', '<=15');
insert into green_in_regulation (id, locale_id, description, short_description) values (1, 0, '> 15''', '>15');
insert into green_in_regulation (id, locale_id, description, short_description) values (2, 0, 'No GIR', 'NG');

-- load golf club lookup data...
insert into golf_club (id, locale_id, description, short_description) values (0, 0, 'Driver', 'D');
insert into golf_club (id, locale_id, description, short_description) values (1, 0, '3-wood', '3W');
insert into golf_club (id, locale_id, description, short_description) values (2, 0, '4-wood', '4W');
insert into golf_club (id, locale_id, description, short_description) values (3, 0, '5-wood', '5W');
insert into golf_club (id, locale_id, description, short_description) values (4, 0, '7-wood', '7W');
insert into golf_club (id, locale_id, description, short_description) values (5, 0, '9-wood', '9W');
insert into golf_club (id, locale_id, description, short_description) values (6, 0, 'Wedge', 'PW');
insert into golf_club (id, locale_id, description, short_description) values (7, 0, '52 wedge', '52W');
insert into golf_club (id, locale_id, description, short_description) values (8, 0, '54 wedge', '54W');
insert into golf_club (id, locale_id, description, short_description) values (9, 0, '56 wedge', '56W');
insert into golf_club (id, locale_id, description, short_description) values (10, 0, '58 wedge', '58W');
insert into golf_club (id, locale_id, description, short_description) values (11, 0, '60 wedge', '60W');
insert into golf_club (id, locale_id, description, short_description) values (22, 0, '64 wedge', '64W');
insert into golf_club (id, locale_id, description, short_description) values (12, 0, 'Chipper', 'CH');
insert into golf_club (id, locale_id, description, short_description) values (13, 0, '1-iron', '1i');
insert into golf_club (id, locale_id, description, short_description) values (14, 0, '2-iron', '2i');
insert into golf_club (id, locale_id, description, short_description) values (15, 0, '3-iron', '3i');
insert into golf_club (id, locale_id, description, short_description) values (16, 0, '4-iron', '4i');
insert into golf_club (id, locale_id, description, short_description) values (17, 0, '5-iron', '5i');
insert into golf_club (id, locale_id, description, short_description) values (18, 0, '6-iron', '6i');
insert into golf_club (id, locale_id, description, short_description) values (19, 0, '7-iron', '7i');
insert into golf_club (id, locale_id, description, short_description) values (20, 0, '8-iron', '8i');
insert into golf_club (id, locale_id, description, short_description) values (21, 0, '9-iron', '9i');

-- load tee color lookup data...
--insert into tee_color (id, color) values (0, 'Red');
--insert into tee_color (id, color) values (1, 'White');
--insert into tee_color (id, color) values (2, 'Blue');
--insert into tee_color (id, color) values (3, 'Gold');
--insert into tee_color (id, color) values (4, 'Green');
--insert into tee_color (id, color) values (5, 'Black');

-- load golf ball lookup data...
insert into golf_ball (id, locale_id, description) values (0, 0, 'Titleist Pro V1');
insert into golf_ball (id, locale_id, description) values (1, 0, 'Titleist Pro V1x');
insert into golf_ball (id, locale_id, description) values (2, 0, 'Maxfli Black Max');

-- load head wear lookup data...
insert into head_wear (id, locale_id, description) values (0, 0, 'None');
insert into head_wear (id, locale_id, description) values (1, 0, 'Visor');
insert into head_wear (id, locale_id, description) values (2, 0, 'Cap');

-- load eye wear lookup data...
insert into eye_wear (id, locale_id, description) values (0, 0, 'None');
insert into eye_wear (id, locale_id, description) values (1, 0, 'Contact lenses');
insert into eye_wear (id, locale_id, description) values (2, 0, 'Glasses');
insert into eye_wear (id, locale_id, description) values (3, 0, 'Sun glasses');

-- load pant wear lookup data...
insert into pant_wear (id, locale_id, description) values (0, 0, 'Pants');
insert into pant_wear (id, locale_id, description) values (1, 0, 'Bermuda shorts');
insert into pant_wear (id, locale_id, description) values (2, 0, 'Knickers');

-- load weather condition lookup data...
insert into weather_condition (id, locale_id, description) values (0, 0, 'Beautiful');
insert into weather_condition (id, locale_id, description) values (1, 0, 'Rainy');
insert into weather_condition (id, locale_id, description) values (2, 0, 'Cold');
insert into weather_condition (id, locale_id, description) values (3, 0, 'Cold and Rainy');
insert into weather_condition (id, locale_id, description) values (4, 0, 'Hot and Humid');	
insert into weather_condition (id, locale_id, description) values (5, 0, 'Windy');	

-- load circumstances lookup data...
-- insert into round_circumstance (id, name) values (0, 'Is Tournament Round');
-- insert into round_circumstance (id, name) values (1, 'Weather Conditions');
-- insert into round_circumstance (id, name) values (2, 'Head Wear');
-- insert into round_circumstance (id, name) values (3, 'Eye Wear');
-- insert into round_circumstance (id, name) values (4, 'Pant Wear');
-- insert into round_circumstance (id, name) values (5, 'Cart or Walked');
-- insert into round_circumstance (id, name) values (6, 'Used Caddie');
-- insert into round_circumstance (id, name) values (7, 'From Date');
-- insert into round_circumstance (id, name) values (8, 'To Date');
-- insert into round_circumstance (id, name) values (9, 'Chart');

-- seed the hi-lo table...
insert into uid_table (next_hi_value_column) values (100);
