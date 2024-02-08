INSERT INTO nb_clients(user_id, first_name, last_name, registration_date)
VALUES (1, 'Ivan', 'Ivanov', '2023-01-17 16:00:00');

INSERT INTO nb_friend_invites(user_from, user_to, status, date_time)
VALUES (1, 20, 'ACCEPTED', '2023-01-17 16:00:00');

INSERT INTO nb_clients_friends(user_from, user_to)
VALUES (1, 20)