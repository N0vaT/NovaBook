DROP TABLE IF EXISTS nb_clients;
DROP TABLE IF EXISTS nb_friend_invites;
DROP TABLE IF EXISTS nb_clients_friends;

CREATE TABLE IF NOT EXISTS nb_clients(
    user_id serial,
    first_name varchar(20),
    last_name varchar(20),
    patronymic varchar(20),
    phone varchar(15),
    email varchar(20),
    birthday timestamp,
    registration_date timestamp NOT NULL,
    avatar_name varchar(100),
    sex varchar(10),
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS nb_friend_invites(
    invite_id serial,
    user_from integer NOT NULL,
    user_to integer NOT NULL,
    status varchar(10) NOT NULL,
    date_time timestamp NOT NULL,
    PRIMARY KEY (invite_id),
    CONSTRAINT fk_nb_invite_user_from FOREIGN KEY (user_from) REFERENCES nb_clients (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_nb_invite_user_to FOREIGN KEY (user_to) REFERENCES nb_clients (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS nb_clients_friends(
    user_from integer NOT NULL,
    user_to integer NOT NULL,
    PRIMARY KEY (user_from, user_to),
    CONSTRAINT fk_nb_clients_friends_user_from FOREIGN KEY (user_from) REFERENCES nb_clients (user_id),
    CONSTRAINT fk_nb_clients_friends_user_to FOREIGN KEY (user_to) REFERENCES nb_clients (user_id)
);