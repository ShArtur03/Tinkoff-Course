CREATE TABLE IF NOT EXISTS subscription(
    id BIGINT PRIMARY KEY NOT NULL,
    chatId BIGINT,
    linkId BIGINT,
    FOREIGN KEY (chatId) REFERENCES chat(id),
    FOREIGN KEY (linkId) REFERENCES link(id)
);