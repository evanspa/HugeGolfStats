-- create 'Paul Evans' player...
insert into player (id, user_id, email_address, firstname, lastname, passwd, title, 
	swings_right_handed, putts_right_handed, eye_wear_id, 
	head_wear_id, pant_wear_id, num_holes_played, wears_vest,
	wears_long_sleeves, birthdate, postal_code, collect_club_used_off_tee,
	collect_tee_shot_distance, collect_num_putts, collect_fairway_hit,
	collect_gir, collect_approach_shot_line, collect_approach_shot_distance,
	collect_sand_save, collect_up_down)
	values (0, 'evanspa', 'paulevans7@yahoo.com', 'Paul', 'Evans', '5f4dcc3b5aa765d61d8327deb882cf99', 
	'Mr.', 1, 1, 2, 1, 0, 18, 0, 0, '1978-03-16', '12188',
	1, 0, 1, 1, 0, 0, 1, 1, 0);

-- create 'admin' and 'user' roles for Paul Evans...
insert into user_roles (role_name, user_id) values ('admin', 'evanspa');
insert into user_roles (role_name, user_id) values ('user',  'evanspa');