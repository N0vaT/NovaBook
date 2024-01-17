DROP TABLE IF EXISTS nb_clients;

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
)