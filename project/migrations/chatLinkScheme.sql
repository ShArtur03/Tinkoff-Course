CREATE TABLE IF NOT EXISTS chatLink(
    chatId INTEGER REFERENCES chat(id) NOT NULL,
    linkId INTEGER REFERENCES link(id) NOT NULL,
    FOREIGN KEY (chatId, linkId)
);