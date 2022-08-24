DROP TABLE messages;
DROP TABLE room;

CREATE TABLE messages (
    id bigserial PRIMARY KEY,
    chat_id VARCHAR(255) NOT NULL,
    sender_id VARCHAR(255) NOT NULL,
    recipient_id VARCHAR(255) NOT NULL,
    added_at VARCHAR(20) NOT NULL,
    content text NOT NULL,
    status boolean NOT NULL
);

CREATE TABLE room (
    id bigserial PRIMARY KEY,
    chat_id VARCHAR(255) NOT NULL,
    sender_id VARCHAR(255) NOT NULL,
    recipient_id VARCHAR(255) NOT NULL
);