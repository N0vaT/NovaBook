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
    user_name varchar(20) UNIQUE not null,
    user_password varchar(100) not null,
    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS nb_users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES nb_users (user_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES nb_roles (role_id)
    );