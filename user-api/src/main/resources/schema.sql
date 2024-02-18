DROP TABLE IF EXISTS nb_clients;

CREATE TABLE IF NOT EXISTS nb_clients(
    user_id serial,
    first_name varchar(20),
    last_name varchar(20),
    PRIMARY KEY (user_id)
)