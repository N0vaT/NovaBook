DROP TABLE IF EXISTS nb_comments;
DROP TABLE IF EXISTS nb_posts;

CREATE TABLE IF NOT EXISTS nb_comments(
    comment_id serial,
    text text NOT NULL,
    date_creation timestamp NOT NULL,
    owner_id integer NOT NULL,
    post_id integer NOT NULL,
    PRIMARY KEY (comment_id),
    CONSTRAINT fk_nb_posts_post_id FOREIGN KEY (post_id) REFERENCES nb_posts (post_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS nb_posts(
    post_id serial,
    post_title varchar,
    post_text text NOT NULL,
    date_creation timestamp NOT NULL,
    owner_id integer NOT NULL,
    status varchar(10) NOT NULL,
    PRIMARY KEY (post_id)
);