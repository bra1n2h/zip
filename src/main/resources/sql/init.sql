CREATE TABLE IF NOT EXISTS company
(
    id INT PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS company_locales
(
    company_id INT REFERENCES company (id) ON DELETE CASCADE,
    lang VARCHAR(2),
    description VARCHAR(128) NOT NULL,
    PRIMARY KEY (company_id, lang)
);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL UNIQUE,
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(30),
    company_id INT REFERENCES company(id),
    image VARCHAR(128),
    password VARCHAR(128) DEFAULT '{noop}123'
);

CREATE TABLE IF NOT EXISTS payment
(
    id SERIAL PRIMARY KEY,
    amount INT NOT NULL,
    receiver_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_chat
(
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    chat_id BIGINT NOT NULL REFERENCES chat(id) ON DELETE CASCADE,
    UNIQUE (user_id, chat_id)
);
