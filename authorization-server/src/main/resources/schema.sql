DROP TABLE IF EXISTS nb_users_roles CASCADE;
DROP TABLE IF EXISTS nb_users CASCADE;
DROP TABLE IF EXISTS nb_roles CASCADE;

CREATE TABLE IF NOT EXISTS nb_roles(
    role_id serial,
    role_name varchar(20) UNIQUE NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS nb_users(
    user_id serial,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(100) UNIQUE NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS nb_users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES nb_users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES nb_roles (role_id) ON UPDATE CASCADE
    );