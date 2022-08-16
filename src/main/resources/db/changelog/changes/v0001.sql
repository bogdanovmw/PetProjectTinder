CREATE TABLE users (
    id serial PRIMARY KEY,
    name VARCHAR (100) UNIQUE NOT NULL,
    email VARCHAR (100) UNIQUE NOT NULL,
    password VARCHAR (255) NOT NULL,
    photo VARCHAR (255) NOT NULL
);

CREATE TABLE liked (
    id bigserial PRIMARY KEY,
    id_user INTEGER NOT NULL,
    id_liked_user INTEGER NOT NULL,
    status boolean
);

CREATE TABLE messages (
    id bigserial PRIMARY KEY,
    sender INTEGER NOT NULL,
    recipient INTEGER NOT NULL,
    added_at VARCHAR(20) NOT NULL,
    message text NOT NULL
);