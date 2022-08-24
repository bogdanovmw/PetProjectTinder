DROP TABLE messages;

CREATE TABLE messages (
    id bigserial PRIMARY KEY,
    chat_id INTEGER NOT NULL,
    sender_id INTEGER NOT NULL,
    recipient_id INTEGER NOT NULL,
    added_at VARCHAR(20) NOT NULL,
    content text NOT NULL,
    status boolean NOT NULL
);

CREATE TABLE room (
    id bigserial PRIMARY KEY,
    chat_id INTEGER NOT NULL,
    sender_id INTEGER NOT NULL,
    recipient_id INTEGER NOT NULL
);